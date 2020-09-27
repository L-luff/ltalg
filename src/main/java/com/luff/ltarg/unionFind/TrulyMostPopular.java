package com.luff.ltarg.unionFind;

import java.util.*;

/**
 * @author lsq
 * @date 2020/9/19
 * 每年，政府都会公布一万个最常见的婴儿名字和它们出现的频率，也就是同名婴儿的数量。有些名字有多种拼法，例如，John 和 Jon 本质上是相同的名字，但被当成了两个名字公布出来。给定两个列表，一个是名字及对应的频率，另一个是本质相同的名字对。设计一个算法打印出每个真实名字的实际频率。注意，如果 John 和 Jon 是相同的，并且 Jon 和 Johnny 相同，则 John 与 Johnny 也相同，即它们有传递和对称性。
 *
 * 在结果列表中，选择字典序最小的名字作为真实名字。
 *
 * 示例：
 *
 * 输入：names = ["John(15)","Jon(12)","Chris(13)","Kris(4)","Christopher(19)"], synonyms = ["(Jon,John)","(John,Johnny)","(Chris,Kris)","(Chris,Christopher)"]
 * 输出：["John(27)","Chris(36)"]
 * 提示：
 *
 * names.length <= 100000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/baby-names-lcci
 *
 */
public class TrulyMostPopular {


    public static void main(String[] args) {
//        String[] s1 = new String[]{"Sqwejn(19)", "Slh(66)", "Lwyfn(50)", "Adlue(171)", "Ghlj(35)", "Xioal(11)", "Jmqqsj(19)", "Orpqim(69)", "Lnow(157)", "Bbcpth(256)", "Bcs(262)", "Vaakt(21)", "Mqgga(31)", "Ndc(62)", "Phlto(41)", "Clplqf(8)", "Jpftyg(96)", "Fayxe(155)", "Slfwyo(82)", "Aasipa(262)", "Jylbla(100)", "Gaoo(98)", "Icsvku(1)", "Vqnjg(142)", "Bvnkk(52)", "Prf(48)", "Bjfkzo(54)", "Ghk(14)", "Xuzubb(90)", "Fshi(62)", "Npbu(62)", "Rmlbnj(98)", "Sfjdjc(62)", "Ccqunq(186)", "Gql(67)", "Oyo(32)", "Zvzm(31)", "Jnij(75)", "Alqw(702)", "Kmrev(31)", "Oip(13)", "Mrqlne(39)", "Kxjw(36)", "Kaaht(28)", "Umt(13)", "Pxzfjg(91)", "Xbesji(70)", "Jxv(152)", "Ttj(28)", "Wzt(41)", "Zhc(44)", "Qlvglt(36)", "Dma(170)", "Axaqkm(100)", "Rqa(117)", "Fzoe(130)", "Iggrg(99)", "Gsiiyo(215)", "Uwy(26)"};
//        String[] s2 = new String[]{"Prf(48)", "Zvzm(31)", "Jmqqsj(19)", "Uwy(26)", "Jylbla(100)", "Xioal(11)", "Npbu(62)", "Jpftyg(96)", "Pxzfjg(91)", "Dma(170)", "Jxv(152)", "Kxjw(36)", "Clplqf(8)", "Slfwyo(82)", "Xbesji(70)", "Fzoe(130)", "Alqw(424)", "Oip(13)", "Sqwejn(19)", "Icsvku(1)", "Vqnjg(142)", "Vaakt(21)", "Rqa(117)", "Axaqkm(100)", "Aasipa(262)", "Gaoo(98)", "Ghlj(35)", "Umt(13)", "Iggrg(99)", "Fayxe(155)", "Zhc(44)", "Slh(66)", "Orpqim(69)", "Gsiiyo(215)", "Sfjdjc(62)", "Ccqunq(186)", "Oyo(32)", "Bvnkk(52)", "Kaaht(28)", "Fshi(62)", "Phlto(41)", "Mqgga(31)", "Adlue(171)", "Lnow(157)", "Rmlbnj(98)", "Jnij(75)", "Ghk(14)", "Mrqlne(39)", "Wzt(41)", "Xuzubb(90)", "Kmrev(31)", "Bbcpth(256)", "Lwyfn(50)", "Qlvglt(36)", "Gql(67)", "Bcs(262)", "Ttj(28)", "Ndc(62)", "Bjfkzo(54)"};
//        ;
//
//        Arrays.sort(s1);
//        Arrays.sort(s2);
//        System.out.println(Arrays.toString(s1));
//        System.out.println(Arrays.toString(s2));
//
//        String[] names = new String[]{"Pwsuo(71)", "Prf(48)", "Rgbu(49)", "Zvzm(31)", "Xxcl(25)", "Bbcpth(42)", "Padz(70)", "Jmqqsj(19)", "Uwy(26)", "Jylbla(65)", "Xioal(11)", "Npbu(62)", "Jpftyg(96)", "Tal(46)", "Hnc(100)", "Yldu(85)", "Alqw(45)", "Wbcxi(34)", "Kxjw(36)", "Clplqf(8)", "Fayxe(66)", "Slfwyo(48)", "Xbesji(70)", "Pmbz(22)", "Oip(2)", "Fzoe(63)", "Qync(79)", "Utc(11)", "Sqwejn(19)", "Ngi(8)", "Gsiiyo(60)", "Bcs(73)", "Icsvku(1)", "Yzwm(92)", "Vaakt(21)", "Uvt(70)", "Axaqkm(100)", "Gyhh(84)", "Gaoo(98)", "Ghlj(35)", "Umt(13)", "Nfimij(52)", "Zmeop(77)", "Vje(29)", "Rqa(47)", "Upn(89)", "Zhc(44)", "Slh(66)", "Orpqim(69)", "Vxs(85)", "Gql(19)", "Sfjdjc(62)", "Ccqunq(93)", "Oyo(32)", "Bvnkk(52)", "Pxzfjg(45)", "Kaaht(28)", "Arrugl(57)", "Vqnjg(50)", "Dbufek(63)", "Fshi(62)", "Lvaaz(63)", "Phlto(41)", "Lnow(70)", "Mqgga(31)", "Adlue(82)", "Zqiqe(27)", "Mgs(46)", "Zboes(56)", "Dma(70)", "Jnij(57)", "Ghk(14)", "Mrqlne(39)", "Ljkzhs(35)", "Rmlbnj(42)", "Qszsny(93)", "Aasipa(26)", "Wzt(41)", "Xuzubb(90)", "Maeb(56)", "Mlo(18)", "Rttg(4)", "Kmrev(31)", "Kqjl(39)", "Iggrg(47)", "Mork(88)", "Lwyfn(50)", "Lcp(42)", "Zpm(5)", "Qlvglt(36)", "Liyd(48)", "Jxv(67)", "Xaq(70)", "Tkbn(81)", "Rgd(85)", "Ttj(28)", "Ndc(62)", "Bjfkzo(54)", "Lqrmqh(50)", "Vhdmab(41)"};
//        String[] synonyms = new String[]{"(Uvt,Rqa)", "(Qync,Kqjl)", "(Fayxe,Upn)", "(Maeb,Xaq)", "(Pmbz,Vje)", "(Hnc,Dma)", "(Pwsuo,Gyhh)", "(Gyhh,Aasipa)", "(Fzoe,Lcp)", "(Mgs,Vhdmab)", "(Qync,Rgd)", "(Gql,Liyd)", "(Gyhh,Tkbn)", "(Arrugl,Adlue)", "(Wbcxi,Slfwyo)", "(Yzwm,Vqnjg)", "(Lnow,Vhdmab)", "(Lvaaz,Rttg)", "(Nfimij,Iggrg)", "(Vje,Lqrmqh)", "(Jylbla,Ljkzhs)", "(Jnij,Mlo)", "(Adlue,Zqiqe)", "(Qync,Rttg)", "(Gsiiyo,Vxs)", "(Xxcl,Fzoe)", "(Dbufek,Xaq)", "(Ccqunq,Qszsny)", "(Zmeop,Mork)", "(Qync,Ngi)", "(Zboes,Rmlbnj)", "(Yldu,Jxv)", "(Padz,Gsiiyo)", "(Oip,Utc)", "(Tal,Pxzfjg)", "(Adlue,Zpm)", "(Bbcpth,Mork)", "(Qync,Lvaaz)", "(Pmbz,Qync)", "(Alqw,Ngi)", "(Bcs,Maeb)", "(Rgbu,Zmeop)"};
//        String[] s3 = new TrulyMostPopular().trulyMostPopular(names, synonyms);
//        Arrays.sort(s3);
//        System.out.println(Arrays.toString(s3));


    }
    public String[] trulyMostPopular(String[] names, String[] synonyms) {
        return solution1(names,synonyms);
    }


    public String[] solution1(String[] names,String[] synonyms){
        Map<String,String> namesMap=new HashMap<>(names.length);
        Map<String,String[]> par=new HashMap<>(names.length);
        for (String name:names){
            int idx=name.indexOf("("),lastIdx=name.indexOf(")");
            String key=name.substring(0,idx),val=name.substring(idx+1,lastIdx);
            namesMap.put(key,val);
            par.put(key,new String[]{key,val});
        }

        for (String s:synonyms){
            int idx=s.indexOf(",");
            String first=s.substring(1,idx),second=s.substring(idx+1,s.length()-1);
            union(par,first,namesMap.getOrDefault(first,"0"),second,namesMap.getOrDefault(second,"0"));
        }
        List<String> res=new ArrayList<>();
        for (Map.Entry<String, String[]> entry : par.entrySet()) {
            if (entry.getKey().equals(entry.getValue()[0])){
                res.add(new StringBuilder().append(entry.getKey()).append("(").append(entry.getValue()[1]).append(")").toString());
            }
        }
        String[] resArr=new String[res.size()];
        return res.toArray(resArr);
    }
    public void union(Map<String,String[]> par,String f,String fv,String s,String sv){
        String[] s1 = find(par, f, fv);
        String[] s2 = find(par, s, sv);
        if (!s1[0].equals(s2[0])) { // 如果有相同的祖先，代表祖先以将将值加上了，所以不需要再次比较
//            s1 = par.get(s1[0]);
//            s2 = par.get(s2[0]);
//            String[] l = s1[0].compareTo(s2[0]) > 0 ? s2 : s1;
//            String[] b = l[0].equals(s1[0]) ? s2 : s1;
//            b[0] = l[0];
//            l[1] = String.valueOf(Integer.parseInt(l[1]) + Integer.parseInt(b[1]));
            if (s1[0].compareTo(s2[0])>0){
                s1[0]=s2[0];
                s2[1]=String.valueOf(Integer.parseInt(s1[1])+Integer.parseInt(s2[1]));
            }else{
                s2[0]=s1[0];
                s1[1]=String.valueOf(Integer.parseInt(s1[1])+Integer.parseInt(s2[1]));
            }
        }
    }
    public String[] find(Map<String,String[]> par,String key,String val){
        String[] res = par.get(key);
        if (res==null){
            res=new String[]{key,val};
            par.put(key,res);
            return res;
        }

        if (!res[0].equals(key)){
            String[] parent = find(par, res[0], null);
            res[0]=parent[0];
            res[1]=parent[1];
            res=parent;
        }
        return res;

    }

}
