
package za.ac.tut.utils;


import java.io.*;
import java.util.*;
import za.ac.tut.cand.PracticalCandidate;

public class PracticalRoster {
    private Set<String> registeredNames;
    
    public PracticalRoster(File rosterFile) {
        
        registeredNames = new HashSet<>();
        
        if (rosterFile.canRead()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(rosterFile))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String trimmedLine = line.trim();
                    if (!trimmedLine.isEmpty()) {
                        registeredNames.add(trimmedLine);
                    }
                }
            } catch (IOException e) {
                System.err.println("Error reading roster file: " + rosterFile.getAbsolutePath());
                e.printStackTrace();
            }
        } else {
            System.err.println("Cannot read roster file: " + rosterFile.getAbsolutePath());
        }
    }
    
    public boolean registered(PracticalCandidate c) {
       // return registeredNames.contains(c.getName().trim());
      
       if(registeredNames.contains(c)){
           c.getName().trim();
           return true;
       }else{
           return false;
       }
    }
    
    public int getTotal() {
      //  return registeredNames.isEmpty() ? 0 : registeredNames.size();
      int length = 0;
      
        if (registeredNames.isEmpty()) {
            return length;
            
        }else{
            return registeredNames.size();
        }
    }
    
    public void appendSessionReport(File out, PracticalCandidate c, String ide, 
                                  Boolean internetDisabled, boolean listed) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(out, true))) {
            writer.println("Candidate: " + c.getName());
            writer.println("Language: " + c.getLanguage());
            writer.println("Duration: " + c.getDuration());
            writer.println("IDE: " + ide);
            writer.println("Internet: " + (internetDisabled ? "Disabled" : "Enabled"));
            writer.println("Listed: " + (listed ? "Yes" : "No"));
            writer.println("---");
        } catch (IOException e) {
            System.err.println("Error writing to report file: " + out.getAbsolutePath());
            e.printStackTrace();
        }
    }
}