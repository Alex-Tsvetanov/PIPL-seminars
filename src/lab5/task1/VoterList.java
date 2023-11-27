package lab5.task1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class VoterList {
    private final List<Voter> voters;
    public VoterList() {
        voters = new ArrayList<>();
    }
    public VoterList addVoter(Voter voter) {
        voters.add(voter);
        return this;
    }
    public List<Voter> getVoters() {
        return voters.stream()
                .filter(x -> x.getCity().compareTo("Sofia") == 0)
                .sorted(Comparator.comparing(Voter::getStreet).thenComparing(Voter::getAddress))
                .collect(Collectors.toList());
    }
}
