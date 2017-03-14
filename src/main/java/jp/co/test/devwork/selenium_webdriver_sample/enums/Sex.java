package jp.co.test.devwork.selenium_webdriver_sample.enums;

public enum Sex {
    FEMALE("女", "sex_female", "0"), MALE("男", "sex_male", "1");

    private Sex(String label, String vlaue, String code) {
        this.label = label;
        this.value = vlaue;
        this.code = code;
    }

    // ラベルの値から該当するEnumを返却する
    public static Sex getValueFromLabel(String label) {
        for (Sex sex : Sex.values()) {
            if (sex.getLabel().equals(label)) {
                return sex;
            }
        }
        throw new IllegalArgumentException();
    }

    private String label;
    private String value;
    private String code;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
