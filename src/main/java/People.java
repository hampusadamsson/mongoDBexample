import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class People implements Serializable{
    private String name;
    private String lastName;
    private List<People> relatives;

    public People() {
    }

    public People(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
        this.relatives = new ArrayList<People>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<People> getRelatives() {
        return relatives;
    }

    public void setRelatives(List<People> relatives) {
        this.relatives = relatives;
    }

    public void print(){
        System.out.println(this.getName() + " " + this.getLastName());
        if(!(this.relatives == null)){
            for (People p : this.relatives){
                System.out.print("\t");
                p.print();
            }
        }
    }

}
