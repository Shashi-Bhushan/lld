package in.shabhushan.lld.expense;

import static java.util.Map.entry;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import in.shabhushan.lld.expense.controller.ExpenseController;
import in.shabhushan.lld.expense.controller.GroupController;
import in.shabhushan.lld.expense.controller.UserController;
import in.shabhushan.lld.expense.dto.request.CreateGroupDTO;
import in.shabhushan.lld.expense.dto.request.ExpenseRequestDTO;
import in.shabhushan.lld.expense.dto.response.SettleUpResponse;
import in.shabhushan.lld.expense.entity.Expense;
import in.shabhushan.lld.expense.entity.Group;
import in.shabhushan.lld.expense.entity.User;
import in.shabhushan.lld.expense.repository.ExpenseRepository;
import in.shabhushan.lld.expense.repository.GroupRepository;
import in.shabhushan.lld.expense.repository.UserRepository;
import in.shabhushan.lld.expense.repository.impl.ExpenseRepositoryImpl;
import in.shabhushan.lld.expense.repository.impl.GroupRepositoryImpl;
import in.shabhushan.lld.expense.repository.impl.UserRepositoryImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Unit test for simple App.
 */
public class AppTest {

    private UserController userController;
    private ExpenseController expenseController;
    private GroupController groupController;

    private static int id = 0;

    private User userOne;
    private User userTwo;
    private User userThree;
    private User userFour;

    private Group group;

    @Before
    public void setup() {
        UserRepository userRepository = new UserRepositoryImpl();
        ExpenseRepository expenseRepository = new ExpenseRepositoryImpl();
        GroupRepository groupRepository = new GroupRepositoryImpl();


        userController = new UserController(userRepository);
        expenseController = new ExpenseController(userRepository, expenseRepository);
        groupController = new GroupController(groupRepository);

        userOne = new User("Shashi", 12345, "password");
        userTwo = new User("Shashi", 12345, "password");
        userThree = new User("Shashi", 12345, "password");
        userFour = new User("Shashi", 12345, "password");

        assertTrue(userController.createUser(userOne));
        assertTrue(userController.createUser(userTwo));
        assertTrue(userController.createUser(userThree));
        assertTrue(userController.createUser(userFour));

        CreateGroupDTO groupRequest = new CreateGroupDTO("GoF", "Gang of Four", Set.of(userOne, userTwo, userThree, userFour), Set.of(), userOne);
        group = groupController.createGroup(groupRequest);
    }

    /**
     * Rigorous Test :-)
     */
    @Test
    public void testApplication() {
        ExpenseRequestDTO expenseRequest = new ExpenseRequestDTO("Restaurant", "restaurant Bill for birthday party",
                Set.of(userOne.getId(), userTwo.getId(), userThree.getId()), userOne.getId(), 3000);

        Expense expense = expenseController.createExpense(expenseRequest, "EqualPayment",
                Map.ofEntries(entry(userOne, 2500.0), entry(userTwo, 500.0), entry(userThree, 0.0)), "MultiPay");

        assertNotNull(expense);

        List<SettleUpResponse> settleUpResponses = expenseController.settleExpense(expense);

        assertFalse(settleUpResponses.isEmpty());

        for (SettleUpResponse res: settleUpResponses) {
            if (res.getSender().equals(userTwo)) {
                assertEquals(500.0, res.getAmount());
                assertEquals(userOne, res.getReceiver());
            } else if (res.getSender().equals(userThree)) {
                assertEquals(1000.0, res.getAmount());
                assertEquals(userOne, res.getReceiver());
            }
        }

        expenseRequest = new ExpenseRequestDTO("Airport Bill", "airport Bill for lounge",
                Set.of(userOne.getId(), userTwo.getId(), userThree.getId(), userFour.getId()), userOne.getId(), 400);

        expense = expenseController.createExpense(expenseRequest, "EqualPayment",
                Map.ofEntries(entry(userOne, 200.0), entry(userTwo, 200.0), entry(userThree, 0.0), entry(userFour, 0.0)), "MultiPay");

        assertNotNull(expense);

        settleUpResponses = expenseController.settleExpense(expense);

        assertFalse(settleUpResponses.isEmpty());

        for (SettleUpResponse res: settleUpResponses) {
            if (res.getSender().equals(userThree)) {
                assertEquals(100.0, res.getAmount());
                assertThat(res.getReceiver(), anyOf(is(userOne), is(userTwo)));
            } else if (res.getSender().equals(userFour)) {
                assertEquals(100.0, res.getAmount());
                assertThat(res.getReceiver(), anyOf(is(userOne), is(userTwo)));
            }
        }

        expenseRequest = new ExpenseRequestDTO("Airport Bill", "airport Bill for lounge",
                Set.of(userOne.getId(), userTwo.getId(), userThree.getId(), userFour.getId()), userOne.getId(), 400);

        expense = expenseController.createExpense(expenseRequest, "EqualPayment",
                Map.ofEntries(entry(userOne, 400.0)), "SelfPay");

        assertNotNull(expense);

        settleUpResponses = expenseController.settleExpense(expense);

        assertFalse(settleUpResponses.isEmpty());

        for (SettleUpResponse res: settleUpResponses) {
            assertEquals(100.0, res.getAmount());
            assertEquals(userOne, res.getReceiver());
        }

        System.out.println(expenseController.getAmountOwed(userOne));
        System.out.println(expenseController.getAmountOwed(userTwo));
        System.out.println(expenseController.getAmountOwed(userThree));
        System.out.println(expenseController.getAmountOwed(userFour));

        // Test settleExpense(User)
        ///////////////////////////

        expenseRequest = new ExpenseRequestDTO("Airport Bill", "airport Bill for lounge",
                Set.of(userOne.getId(), userTwo.getId(), userThree.getId(), userFour.getId()), userOne.getId(), 2400);

        expense = expenseController.createExpense(expenseRequest, "EqualPayment",
                Map.ofEntries(entry(userOne, 800.0), entry(userThree, 1600.0)), "MultiPay");

        assertNotNull(expense);

        settleUpResponses = expenseController.settleExpense(userTwo);

        assertFalse(settleUpResponses.isEmpty());

        assertTrue(settleUpResponses.stream().anyMatch(res -> res.getReceiver().equals(userThree) && res.getAmount() == 400.0d));
        assertTrue(settleUpResponses.stream().anyMatch(res -> res.getReceiver().equals(userOne) && res.getAmount() == 200.0d));


        // Test settleExpense(Group)
        ///////////////////////////
        settleUpResponses = expenseController.settleExpense(group);

        System.out.println(settleUpResponses);
    }
}
