class Employee {
    String name;
    String email;
    int experience;

    Employee(String name, String email, int experience) {
        this.name = name;
        this.email = email;
        this.experience = experience;
    }

    String getName() {
        return this.name;
    }
    
    String getEmail() {
        return this.email;
    }
    
    int getExperience() {
        return this.experience;
    }
}

class Developer extends Employee {
    String mainLanguage;
    String[] skills;

    public Developer(String name, String email, int experience, String mainLanguage, String[] skills) {
        super(name, email, experience);
        this.mainLanguage = mainLanguage;
        this.skills = skills.clone();
    }

    public String getMainLanguage() {
        return mainLanguage;
    }

    public String[] getSkills() {
        return skills.clone();
    }
}

class DataAnalyst extends Employee {
    boolean phd;
    String[] methods;

    DataAnalyst(String name, String email, int experience, boolean phd, String[] methods) {
        super(name, email, experience);
        this.phd = phd;
        this.methods = methods.clone();
    }

    boolean isPhd() {
        return this.phd;
    }
    
    String[] getMethods() {
        return this.methods.clone();
    }
}
