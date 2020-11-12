/**
 * @auth Nannf
 * @date 2020/11/12 17:50
 * @description: 给你一个有效的 IPv4 地址 address，返回这个 IP 地址的无效化版本。
 * <p>
 * 所谓无效化 IP 地址，其实就是用 "[.]" 代替了每个 "."。
 * <p>
 * 输入：address = "1.1.1.1"
 * 输出："1[.]1[.]1[.]1"
 * <p>
 * 输入：address = "255.100.50.0"
 * 输出："255[.]100[.]50[.]0"
 * <p>
 * 今天选择这个题目是因为外公(1934-4-10 ~ 2020-11-08 21:45) 享年87岁，今天送殡。
 */
public class Solution_1108 {

    public String defangIPaddr(String address) {
        return address.replaceAll("\\.","[\\.]");
    }
}
