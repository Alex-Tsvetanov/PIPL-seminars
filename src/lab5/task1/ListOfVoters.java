package lab5.task1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ListOfVoters {
    private final List<Voter> voters;
    public ListOfVoters() {
        voters = new ArrayList<>();
    }
    public ListOfVoters addVoter(Voter... voters) {
        this.voters.addAll(Arrays.asList(voters));
        return this;
    }
    public List<Voter> getVoters() {
        return voters.stream()
                .filter(x -> x.getCity().compareTo("Sofia") == 0)
                .sorted(Comparator.comparing(Voter::getStreet).thenComparing(Voter::getAddress))
                .collect(Collectors.toList());
    }
}
