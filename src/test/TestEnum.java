public enum TestEnum {
    ZHANGSAN("张三","28"),
    LISI("李四","20");
    private String name;
    private String age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    TestEnum(String name, String age) {
        this.name = name;
        this.age = age;
    }
    }
