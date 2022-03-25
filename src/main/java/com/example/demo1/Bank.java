package com.example.demo1;

import javafx.util.Pair;

import java.util.*;

public class Bank {
    static class Task5{

        private final String[] vectors = {"1111", "00", "10101000", "1100", "10101111", "00000001", "11101011", "01011100"};
        private final Integer[] powers = {2, 1, 3, 2, 3, 3, 3, 3};
        private  StringBuilder[] essVar = new StringBuilder[8];
        private  StringBuilder[] dumVar = new StringBuilder[8];

        Task5() {
            initVars();
        }
        private void initVars() {

            for (int ind = 0; ind < 8; ++ind) {
                essVar[ind] = new StringBuilder();
                dumVar[ind] = new StringBuilder();

                String s = vectors[ind];
                int len = powers[ind];

                for (int i = 1; i <= len; ++i) {
                    if (Objects.requireNonNull(Solution.main(s, 0, i)).equals(Solution.main(s, 1, i))){
                        if (!dumVar[ind].isEmpty())
                            dumVar[ind].append(",");
                        dumVar[ind].append(i);
                    }
                    else {
                        if (!essVar[ind].isEmpty())
                            essVar[ind].append(",");
                        essVar[ind].append(i);
                    }

                }
            }
        }

        public int getPowers(int ind) {
            return powers[ind];
        }
        public String getVector(int ind) {
            return vectors[ind];
        }

        public StringBuilder getEssVar(int ind) {
            return essVar[ind];
        }

        public StringBuilder getDumVar(int ind) {
            return dumVar[ind];
        }
    }

    static class Task11{


    }

    static public class Task3 {
        private boolean[] res0;
        private boolean[] res1;
        private int argumentNumber;
        private boolean[] function;
        private void processFunction() throws IllegalArgumentException {

            if (this.res0.length != this.res1.length
                    || (this.res0.length & (this.res0.length - 1)) != 0
                    || (this.res1.length & (this.res1.length - 1)) != 0
                    || this.res0.length + this.res1.length < 1<<argumentNumber) {
                throw new IllegalArgumentException("length error");
            }

            this.function = new boolean[res0.length + res1.length];
            int a =  this.function.length / ( (int) (Math.pow(2, argumentNumber - 1)) );
            int res0_ind = 0;
            int res1_ind = 0;
            for (int i=0;i<function.length;i++) {
                if (i % a < a / 2) {
                    function[i] = res0[res0_ind];
                    res0_ind++;
                }
                else {
                    function[i] = res1[res1_ind];
                    res1_ind++;
                }
            }
        }
        public Task3(boolean[] res0, boolean[] res1, int argumentNumber) throws IllegalArgumentException {
            this.res0 = new boolean[res0.length];
            System.arraycopy(res0, 0, this.res0, 0, res0.length);
            this.res1 = new boolean[res1.length];
            System.arraycopy(res1, 0, this.res1, 0, res1.length);
            this.argumentNumber = argumentNumber;

            processFunction();
        }

        public boolean[] getRes0() {
            return res0;
        }

        public boolean[] getRes1() {
            return res1;
        }

        public int getArgumentNumber() {
            return argumentNumber;
        }

        public boolean[] getFunction() {
            return function;
        }
    }

    static public class Task6 {
        private boolean[] function;
        private String dnf;
        private String dnfView;

        private void SimplifyDnf() throws IllegalArgumentException {
            String str = "";
            char last = ' ';
            int n = dnf.length();
            int balance = 0;
            boolean isVar = false;
            for (int i=0;i<n;i++) {
                char ch = dnf.charAt(i);
                if (ch != 'v' && ch != 'V' && ('a' <= ch && ch <= 'z' || 'A' <= ch && ch <= 'Z')) {
                    last = ch;
                    isVar = true;
                    str += ch;
                }
                else if ('0' <= ch && ch <= '9') {
                    last = ch;
                    if (isVar) {
                        str += ch;
                    }
                    else {
                        throw new IllegalArgumentException("constants cannot be in dnf ");
                    }
                }
                else if (ch == '(') {
                    last = '(';
                    balance++;
                }
                else if (ch == ')') {
                    if (balance == 0) {
                        throw new IllegalArgumentException();
                    }
                    else {
                        balance--;
                    }
                }
                else if (ch == '-') {
                    last = '-';
                    str += ch;
                }
                else if (ch == '&') {
                }
                else if (ch == 'v' || ch == 'V') {
                    if (last == 'v' || last == '-' || last == '(')
                        throw new IllegalArgumentException();
                    last = 'v';
                    if (balance > 0) {
                        throw new IllegalArgumentException("not a dnf");
                    }
                    if (str.equals("") || i != n-1) {
                        str += 'v';
                    }
                }
            }
            if (balance > 0)
                throw new IllegalArgumentException("not a dnf");
            if (str.charAt(str.length()-1) == 'v')
                throw new IllegalArgumentException();
            dnfView = str;
        }
        public Task6(boolean[] function, String dnf) throws IllegalArgumentException {

            this.function = new boolean[function.length];

            System.arraycopy(function, 0, this.function, 0, function.length);
            this.dnf = dnf;

            SimplifyDnf();
        }

        public boolean[] getFunction() {
            return function;
        }

        public String getDnf() {
            return dnf;
        }

        public String getDnfView() {
            return dnfView;
        }

        public boolean isCorrect() throws IllegalArgumentException {
            // dnf to Array
            String tdnf = dnfView;
            tdnf += 'v';
            int k = 0;
            int sz = function.length;
            if ((sz & (sz - 1)) != 0)
                throw new IllegalArgumentException("illegal size of vector");
            while (sz > 1) {
                k++;
                sz = sz >> 1;
            }
            sz = function.length;

            int arrSize = 0;
            for (int i=0;i<tdnf.length();i++) {
                if (tdnf.charAt(i) == 'v') {
                    arrSize++;
                }
            }
            int[][] dnfArray = new int[arrSize][k];
            for (int i=0;i<arrSize;i++)
                for (int j=0;j<k;j++)
                    dnfArray[i][j] = 0;
            int arrCount = 0;
            Map<String, Integer> varMap = new HashMap<>();
            int varIndex = 0;
            boolean isInverse = false;
            boolean isBuildVar = false;
            String var = "";
            for (int i=0;i<tdnf.length();i++) {
                char ch = tdnf.charAt(i);
                if (ch == '-') {
                    if (isBuildVar) {
                        if (!varMap.containsKey(var)) {
                            varMap.put(var, varIndex);
                            varIndex++;
                        }
                        if (varMap.get(var) >= k)
                            throw new IllegalArgumentException("too many variables");
                        if (isInverse) {
                            dnfArray[arrCount][varMap.get(var)] = 2;
                            isInverse = false;
                        }
                        else {
                            dnfArray[arrCount][varMap.get(var)] = 1;
                        }
                        isBuildVar = false;
                        isInverse = true;
                    }
                    else {
                        isInverse = !isInverse;
                    }
                }
                else if ('0' <= ch && ch <= '9') {
                    var += ch;
                }
                else if (ch != 'v' && ('a' <= ch && ch <= 'z' || 'A' <= ch && ch <= 'Z')) {
                    if (isBuildVar) {
                        if (!varMap.containsKey(var)) {
                            varMap.put(var, varIndex);
                            varIndex++;
                        }
                        if (varMap.get(var) >= k)
                            throw new IllegalArgumentException("too many variables");
                        if (isInverse) {
                            dnfArray[arrCount][varMap.get(var)] = 2;
                            isInverse = false;
                        }
                        else {
                            dnfArray[arrCount][varMap.get(var)] = 1;
                        }
                    }
                    else {
                        isBuildVar = true;
                    }
                    var = "" + ch;
                }
                else {
                    isBuildVar = false;
                    if (!varMap.containsKey(var)) {
                        varMap.put(var, varIndex);
                        varIndex++;
                    }
                    if (varMap.get(var) >= k)
                        throw new IllegalArgumentException("too many variables");
                    if (isInverse) {
                        dnfArray[arrCount][varMap.get(var)] = 2;
                        isInverse = false;
                    }
                    else {
                        dnfArray[arrCount][varMap.get(var)] = 1;
                    }
                    var = "";
                    arrCount++;
                }
            }
            // build dnf function vector
            int n = 0;
            int mx = 1<<k;
            boolean[] dnfVector = new boolean[sz];
            for (int i=0;i<sz;i++)
                dnfVector[i] = false;
            while (n < mx) {
                for (int i=0;i<arrSize;i++) {
                    boolean f = true;
                    for (int j=0;j<k;j++) {
                        int tmp = n & (1<<(k-j-1));
                        boolean fj = !(tmp == 0);
                        if (dnfArray[i][j] == 1) {
                            f &= fj;
                        }
                        else if (dnfArray[i][j] == 2) {
                            f &= !fj;
                        }
                    }

                    if (f) {
                        dnfVector[n] = f;
                        break;
                    }
                }
                n++;
            }
            // dnfVector == function ?
            boolean isEquals = true;
            for (int i=0;i<sz;i++) {
                isEquals &= (function[i] == dnfVector[i]);
            }
            return isEquals;
        }
    }

    static public class Task9 {
        private boolean[] function;
        private String sknf;
        public Task9(boolean[] function) {
            this.function = new boolean[function.length];
            System.arraycopy(function, 0, this.function, 0, function.length);

            int sz = function.length;
            if ((sz & (sz - 1)) != 0)
                throw new IllegalArgumentException();
            int k = 0;
            while (sz > 1) {
                k++;
                sz >>= 1;
            }
            StringBuilder str = new StringBuilder();
            for (int n=0;n<function.length;n++) {
                if (!function[n]) {
                    for (int i=1;i<=k;i++) {
                        int tmp = n & (1<<(k - i));
                        boolean xi = !(tmp == 0);
                        if (xi) {
                            str.append(String.format("-x%d", i));
                        }
                        else {
                            str.append(String.format("x%d", i));
                        }
                        if (i != k) {
                            str.append('v');
                        }
                    }
                    str.append('&');
                }
            }
            if (str.length() > 0 && str.charAt(str.length()-1) == '&') {
                str.deleteCharAt(str.length()-1);
            }
            sknf = str.toString();
        }

        public boolean[] getFunction() {
            return function;
        }

        public String getSknf() {
            return sknf;
        }
    }

    static public class Task12 {
        private boolean[] function;
        private int min;
        private Set<String> minSet;
        private Set<String> processingSet;
        private Map<Integer, Integer> OnesMatch;
        public Task12(boolean[] function){
            this.function = new boolean[function.length];
            System.arraycopy(function, 0, this.function, 0, function.length);
        }
        private Set<String> simplifySet(Set<String> in, int n) {
            Set<String> st = new HashSet<>(in);
            // строки в множестве состоят из символов '0', '1', '2'
            // '0' - значит в наборе на данном месте стоит 0
            // '1' - значит в наборе на данном месте стоит 1
            // '2' - значит в наборе на данном месте стоит прочерк

            // прочерков может быть не более n - количества переменных
            // повторяем n раз, на каждом шаге снова разделяя полученные склейки на группы
            for (int count=0;count<n;count++) {
                // Группируем элементарные конъюнкции по позиции прочерков

                Map<String, Set<String>> groupedByLine = new HashMap<>();
                // ключ - строка, в которой перечислены номера позиций прочерков

                for (String el : st) {
                    StringBuilder str = new StringBuilder();
                    for (int i=0;i<el.length();i++) {
                        if (el.charAt(i) == '2') {
                            str.append(i);
                        }
                    }
                    if (!groupedByLine.containsKey(str.toString())) {
                        groupedByLine.put(str.toString(), new HashSet<String>());
                    }
                    groupedByLine.get(str.toString()).add(el);
                }
                st = new HashSet<>();

                // группируем элементарные конъюнкции по числу единиц отдельно в каждой группе
                // и вычисляем всевозможные склейки в каждой группе
                for (String groupKey : groupedByLine.keySet()) {

                    // группируем по числу единиц
                    Map<Integer, Set<Pair<String, Boolean>>> groupedByOnes = new HashMap<>();
                    for (String str : groupedByLine.get(groupKey)) {
                        int k1 = 0;
                        for (int i=0;i<str.length();i++) {
                            if (str.charAt(i) == '1') {
                                k1++;
                            }
                        }
                        if (!groupedByOnes.containsKey(k1)) {
                            groupedByOnes.put(k1, new HashSet<Pair<String, Boolean>>());
                        }
                        groupedByOnes.get(k1).add(new Pair<String, Boolean>(str, true));
                    }

                    // вычисляем всевозможные склейки
                    for (int k1=0;k1<=n;k1++) {

                        // если для данного числа единиц есть наборы
                        // и для последущего числа единиц есть наборы
                        if (groupedByOnes.containsKey(k1) && groupedByOnes.containsKey(k1+1)) {

                            // для каждого набора в текущей группе
                            // пытаемся склеить со всеми наборами из последующей группы
                            // помечаем все наборы, для которых склейка удалась
                            // чтобы не добавлять их в множество склеек
                            for (Pair<String, Boolean> el1 : groupedByOnes.get(k1)) {
                                boolean flag = true;
                                Set<String> innerToReplace = new HashSet<>();
                                for (Pair<String, Boolean> el2 : groupedByOnes.get(k1+1)) {

                                    int div = 0;
                                    for (int i=0;i<n;i++) {
                                        if (el1.getKey().charAt(i) != el2.getKey().charAt(i)) {
                                            div++;
                                        }
                                    }
                                    if (div == 1) {
                                        flag = false;
                                        innerToReplace.add(el2.getKey());
                                        for (int i=0;i<n;i++) {
                                            if (el1.getKey().charAt(i) != el2.getKey().charAt(i)) {
                                                StringBuilder str = new StringBuilder(el1.getKey());
                                                str.replace(i, i+1, "2");
                                                st.add(str.toString());
                                                break;
                                            }
                                        }
                                    }

                                }
                                for (String s : innerToReplace) {
                                    groupedByOnes.get(k1+1).remove(new Pair<>(s, true));
                                    groupedByOnes.get(k1+1).add(new Pair<>(s, false));
                                }
                                if (flag) {
                                    st.add(el1.getKey());
                                }
                            }
                        }

                        // если нет последующего набора для данного числа единиц
                        // проверяем на наличие пометок
                        // и добавляем в случае отсутствия пометки
                        else if (groupedByOnes.containsKey(k1)) {
                            for (Pair<String, Boolean> el1 : groupedByOnes.get(k1)) {
                                if (el1.getValue()) {
                                    st.add(el1.getKey());
                                }
                            }
                        }
                    }
                }
            }

            return st;
        }

        // функция, проверяющая, включает ли в себя набор a набор b
        private boolean isImplements(String a, String b) {
            for (int i=0;i<a.length();i++) {
                if (a.charAt(i) != '2' && a.charAt(i) != b.charAt(i)) {
                    return false;
                }
            }
            return true;
        }

        // функция подсчёта числа вхождений переменных
        private int variablesCount(Set<String> st) {
            int count = 0;
            for (String str : st) {
                for (int i=0;i<str.length();i++) {
                    if (str.charAt(i) != '2')
                        count++;
                }
            }
            return count;
        }

        // функция минимизации числа вхождений переменных посредством перебора вариантов
        private void minimizeVariables(int pos, Map<Integer, Set<String>> onesImplements, Map<String, Set<Integer>> implementsSets, Map<Integer, Integer> next) {

            // позиция -1 означает, что дизъюнкция полученного множества склеек
            // эквивалентна исходной функции, значит, можно сравнивать с минимальной
            if (pos == -1) {
                int varCount = variablesCount(processingSet);
                if (varCount < min) {
                    min = varCount;
                    minSet = new HashSet<>(processingSet);
                }
            }
            else {

                // 0 в OnesMatch означает, что данные набор
                // ещё не перекрывался какой-либо уже находящейся в множестве склейкой
                // в противном случае, там сохранён номер набора,
                // в котором произошло перекрытие данного набора
                if (OnesMatch.get(pos) != 0) {
                    minimizeVariables(next.get(pos), onesImplements, implementsSets, next);
                }
                else {

                    // для каждой возможной склейки
                    for (String el : onesImplements.get(pos)) {

                        // добавляем данную склейку в набор
                        // перекрываем наборы
                        processingSet.add(el);
                        for (Integer i : implementsSets.get(el)) {
                            if (OnesMatch.get(i) == 0) {
                                OnesMatch.replace(i, pos);
                            }
                        }

                        minimizeVariables(next.get(pos), onesImplements, implementsSets, next);

                        // возвращаем состояние исходное состояние множества
                        // и удаляем метки с перекрывающихся наборов
                        processingSet.remove(el);
                        for (Integer i : implementsSets.get(el)) {
                            if (OnesMatch.get(i) == pos) {
                                OnesMatch.replace(i, 0);
                            }
                        }
                    }
                }
            }
        }

        // Нахождение минимальной ДНФ методом Мак - Класки
        public String getMinDNF() {

            // подготовка вектора булевой функции
            // перевод вектора типа boolean в строку
            int n = 0;
            {
                int k = 1;
                while (k < function.length) {
                    n++;
                    k *= 2;
                }
            }
            Set<String> st = new HashSet<>();
            for (int i=0;i< function.length;i++) {
                if (function[i]) {
                    StringBuilder el = new StringBuilder();
                    for (int j=0;j<n;j++) {
                        int tmp = ((i & (1<<(n-j-1))) == 0 ? 0 : 1);
                        el.append(tmp);
                    }
                    st.add(el.toString());
                }

            }

            // функция находит множество всевозможных склеек
            Set<String> simplified = simplifySet(st, n);

            // для каждой склейки находим наборы,
            // на которых функция принимает значение 1
            // и склейка даёт 1

            // нумеруем наборы
            // для каждого набора сохраняем соответствующие склейки

            // для каждой склейки наборы, соответствующие склейкам
            Map<String, Set<Integer>> implementsSets = new HashMap<>();
            for (String el : simplified) {
                implementsSets.put(el, new HashSet<>());
            }

            // нумерованные наборы
            Map<Integer, String> ones = new HashMap<>();
            for (int i=0;i< function.length;i++) {
                if (function[i]) {
                    StringBuilder el = new StringBuilder();
                    for (int j=0;j<n;j++) {
                        int tmp = ((i & (1<<(n-j-1))) == 0 ? 0 : 1);
                        el.append(tmp);
                    }
                    ones.put(i+1, el.toString());
                }
            }

            // для каждого набора соответствующие склейки
            Map<Integer, Set<String>> onesImplements = new HashMap<>();
            for (Integer i : ones.keySet()) {
                onesImplements.put(i, new HashSet<>());
                for (String el : simplified) {
                    if (isImplements(el, ones.get(i))) {
                        onesImplements.get(i).add(el);
                        implementsSets.get(el).add(i);
                    }
                }
            }

            // подготавливаем данные и находим минимизируем количество вхождений переменных
            Map<Integer, Integer> next = new HashMap<>();
            int end = -1, begin = 0;
            for (Integer i : onesImplements.keySet()) {
                if (end != -1) {
                    next.put(end, i);
                }
                else {
                    begin = i;
                }
                end = i;
            }
            next.put(end, -1);
            OnesMatch = new HashMap<>();
            for (Integer i : ones.keySet()) {
                OnesMatch.put(i, 0);
            }
            processingSet = new HashSet<>();
            minSet = new HashSet<>(simplified);
            min = variablesCount(simplified);
            minimizeVariables(begin, onesImplements, implementsSets, next);

            // построение строки - минимальной ДНФ
            StringBuilder dnf = new StringBuilder();
            for (String K : minSet) {
                for (int i=0;i<n;i++) {
                    if (K.charAt(i) == '1') {
                        dnf.append('x');
                        dnf.append(i+1);
                    }
                    else if (K.charAt(i) == '0') {
                        dnf.append('-');
                        dnf.append('x');
                        dnf.append(i+1);
                    }
                }
                dnf.append('v');
            }
            dnf.deleteCharAt(dnf.length()-1);
            if (dnf.length() == 0) {
                return "x1v-x1";
            }
            return dnf.toString();
        }
    }
}
