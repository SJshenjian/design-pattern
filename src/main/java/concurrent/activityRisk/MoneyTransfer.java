package concurrent.activityRisk;

/**
 * @author : Jian Shen
 * @version : V1.0
 * @date : 2018/6/2
 */
public class MoneyTransfer {
    private static final Object tieLock = new Object();

    public void transferMoney(final Account fromAccount, final Account toAccount, final DollarAmount amount) throws InsufficientFundsException {
        class Helper {
            public void transfer() throws InsufficientFundsException {
                if (fromAccount.getBalance().compareTo(amount) < 0) {
                    throw new InsufficientFundsException();
                } else {
                    fromAccount.debit(amount);
                    toAccount.credit(amount);
                }
            }
        }
        int fromHash = System.identityHashCode(fromAccount);
        int toHash = System.identityHashCode(toAccount);

        if (fromHash < toHash) {
            synchronized(fromAccount) {
                synchronized(toAccount) {
                    new Helper().transfer();
                }
            }
        } else if(fromHash > toHash) {
            synchronized(toAccount) {
                synchronized (fromAccount) {
                    new Helper().transfer();
                }
            }
        } else {
            synchronized (tieLock) {
                synchronized (fromAccount) {
                    synchronized (toAccount) {
                        new Helper().transfer();
                    }
                }
            }
        }
    }
}
