import java.util.*;

public class MyClass {
    private Date m_time;
    private String m_name;
    private List<Long> m_numbers;
    private List<String> m_strings;
    private Set<Long> set;

    public MyClass(Date time, String name, List<Long> numbers, List<String>
            strings) {
        m_time = time;
        m_name = name;
        m_numbers = numbers;
        m_strings = strings;
        set = new HashSet<>(numbers);
    }
    public boolean equals(Object obj) {
        if (obj instanceof MyClass) {
            return ((MyClass) obj).m_time.equals(m_time) &&
                    ((MyClass) obj).m_name.equals(m_name) &&
                    ((MyClass) obj).m_numbers.equals(m_numbers) &&
                    ((MyClass) obj).m_strings.equals(m_strings);
        }
        return false;
    }
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(m_name);
        for (Long item : m_numbers) {
            sb.append(" ").append(item);
        }
        return sb.toString();
    }

    /*
        assuming that the method removes all appearances of str in m_strings
     */
    public void removeString(String str) {
        List<String> tmp = new ArrayList<>();
        for (String s : m_strings) {
            if (!s.equals(str)) {
                tmp.add(s);
            }
        }
        m_strings.clear();
        m_strings.addAll(tmp);
        tmp.clear();
    }
    public boolean containsNumber(long number) {
        return set.contains(number);
    }

    public boolean isHistoric() {
        return m_time.before(new Date());
    }
}
