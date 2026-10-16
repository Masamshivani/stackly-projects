package StudentmangPackage;

public class Candidate {

    private String candidateId;
    private String candidateName;

    public Candidate(String candidateId, String candidateName) {
        this.candidateId = candidateId;
        this.candidateName = candidateName;
    }

    public String getCandidateId() {
        return candidateId;
    }

    public String getCandidateName() {
        return candidateName;
    }
}
