//1.6 
package za.ac.tut.cand;


public class PracticalCandidate {
    private String name;
    private String language;
    private String duration;
    
    public PracticalCandidate(String name, String language, String duration) {
        this.name = name;
        this.language = language;
        this.duration = duration;
    }
    
    public String getName() {
        return name;
    }
    
    public String getLanguage() {
        return language;
    }
    
    public String getDuration() {
        return duration;
    }
}