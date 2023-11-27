package lab5.task1;

public class Main {
    public static void main(String[] args) {
        VoterList v = new VoterList();
        v.addVoter(new Voter("Name1", "Sofia", "Street 1", "3"));
        v.addVoter(new Voter("Name2", "Not Sofia", "Street 1", "1"));
        v.addVoter(new Voter("Name3", "Sofia", "Street 2", "1"));
        v.addVoter(new Voter("Name4", "Not Sofia", "Street 1", "1"));
        v.addVoter(new Voter("Name5", "Sofia", "Street 1", "1"));
        for (Voter x : v.getVoters()) {
            System.out.println(x);
        }
    }
}
