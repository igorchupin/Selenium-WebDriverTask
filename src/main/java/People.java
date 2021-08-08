public class People {

    private String name;
    private String position;
    private String office;
    private Integer age;
    private Integer salary;


    public People(String name, String position, String office, Integer age, Integer salary) {
        this.name = name;
        this.position = position;
        this.office = office;
        this.age = age;
        this.salary = salary;
    }
    @Override
    public String toString () {
        String result = "Name: " + this.name + "; position: " + this.position + "; office: " + this.office +
        "; age: " + this.age +"; salary "  + this.salary + "; " + getClass();
        System.out.println(result);
       return result;
    }
}
