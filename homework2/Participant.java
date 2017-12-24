package homework2;

import java.util.ArrayList;
import java.util.Iterator;

public class Participant implements Simulatable<String> {

    private final double fee;
    ArrayList<Transaction> storageBuffer;
    ArrayList<Transaction> workingBuffer;
    private String label;

    /**
     * @requires fee >= 0, label != null
     * @modifies this
     * @effects constructs a new Participant
     */
    public Participant(double fee, String label) {
        this.fee = fee;
        this.label = label;
        storageBuffer = new ArrayList<Transaction>();
        workingBuffer = new ArrayList<Transaction>();
    }

    /**
     * @modifies this, graph
     * @effects Simulates this, goes over all transactions that are currently in
     *          this.workingBuffer and transfers them to this.storageBuffer or takes
     *          a fee in the amount this.fee and transfer them to the channel
     *          connected to it. will not transfer transaction if channel is full.
     */
    @Override
    public void simulate(BipartiteGraph<String> graph) {        
        Channel c = null;
        Iterator<String> channelIterator = graph.getChildren(this.label).iterator();
        // there could either be one or zero children (connected outgoing channel) 
        if(channelIterator.hasNext()) {
            String channelLabel = channelIterator.next();
            c = (Channel) graph.getNodeData(channelLabel);
        }
        
        Iterator<Transaction> workIterator = workingBuffer.iterator();
        while (workIterator.hasNext()) {
            Transaction t = workIterator.next();
            Transaction newTransaction = new Transaction(t.getDest(), t.getValue() - this.fee);

            if (t.getDest().equals(this.label)) {
                workIterator.remove();
                this.storageBuffer.add(t);
            } else if (c != null && c.canReceiveTransaction(newTransaction)) {
                workIterator.remove();
                c.receiveTransaction(newTransaction);
                if(fee > 0) {
                    Transaction feeTransaction = new Transaction(this.label, this.fee);
                    this.storageBuffer.add(feeTransaction);
                }
            }
        }    
    }

    /**
     * @modifies this.workingBuffer
     * @effects adds a transaction to the working queue. will be handled on the next
     *          call to simulate.
     */
    public boolean receiveTransaction(Transaction newTransaction) {
        workingBuffer.add(newTransaction);
        return true;
    }

    /**
     * @return the sum of all transaction targeting this Participant. including
     *         transactions created by transaction fees.
     */
    public double getBalance() {
        double sum = 0;
        for (Transaction t : this.storageBuffer) {
            sum = sum + t.getValue();
        }
        return sum;
    }
}
