package jdk.INio.regex;

import java.io.File;
import java.io.FileReader;
import java.io.LineNumberReader;
import java.io.IOException;
import java.util.List;
import java.util.LinkedList;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Object-oriented grep
 * <p>
 * A file searching class, similar to grep, which returns information
 * about lines matched in the specified files. Instances of this class
 * are tied to a specific regular expression pattern and may be applied
 * repeatedly to multiple files. Instances of Grep are thread safe, they
 * may be shared.
 *
 * @author Michael Daudel (mgd@ronsoft.com) (original)
 * @author Ron Hitchens (ron@ronsoft.com) (hacked)
 * @author Jian Shen
 * @version V1.0
 * @date 2018/11/15
 */
public class Grep {

    // 用于该实例的模式
    private Pattern pattern;

    /**
     * Instantiate a Grep object for the given pre-compiled Pattern object.
     *
     * @param pattern A java.util.regex.Pattern object specifying the
     *                pattern to search for.
     */
    public Grep(Pattern pattern) {
        this.pattern = pattern;
    }

    /**
     * Instantiate a Grep object and compile the given regular expression
     * string.
     *
     * @param regex      The regular expression string to compile into a
     *                   Pattern for internal use.
     * @param ignoreCase If true, pass Pattern.CASE_INSENSITIVE to the
     *                   Pattern constuctor so that seaches will be done without regard
     *                   to alphabetic case. Note, this only applies to the ASCII
     *                   character set. Use embedded expressions to set other options.
     */
    public Grep(String regex, boolean ignoreCase) {
        this.pattern = Pattern.compile(regex, (ignoreCase) ? Pattern.CASE_INSENSITIVE : 0);
    }

    /**
     * Instantiate a Grep object with the given regular expression string,
     * with default options.
     */
    public Grep(String regex) {
        this(regex, false);
    }

    /**
     * Perform a grep on the given file.
     *
     * @param file A File object denoting the file to scan for the
     *             regex given when this Grep instance was constructed.
     * @return A type-safe array of Grep.MatchedLine objects describing
     * the lines of the file matched by the pattern.
     * @throws IOException If there is a problem reading the file.
     */
    public MatchedLine[] grep(File file) throws IOException {
        List list = grepList(file);
        MatchedLine matches[] = new MatchedLine[list.size()];
        list.toArray(matches);
        return (matches);
    }

    /**
     * Perform a grep on the given file.
     *
     * @param file A String filename denoting the file to scan for the
     *             regex given when this Grep instance was constructed.
     * @return A type-safe array of Grep.MatchedLine objects describing
     * the lines of the file matched by the pattern.
     * @throws IOException If there is a problem reading the file.
     */
    public MatchedLine[] grep(String fileName) throws IOException {
        return (grep(new File(fileName)));
    }

    /**
     * Perform a grep on the given list of files. If a given file cannot
     * be read, it will be ignored as if empty.
     *
     * @param files An array of File objects to scan.
     * @return A type-safe array of Grep.MatchedLine objects describing
     * the lines of the file matched by the pattern.
     */
    public MatchedLine[] grep(File[] files) {
        List aggregate = new LinkedList();
        for (int i = 0; i < files.length; i++) {
            try {
                List temp = grepList(files[i]);
                aggregate.addAll(temp);
            } catch (IOException e) {
                // 忽略I/O异常
            }
        }
        MatchedLine matches[] = new MatchedLine[aggregate.size()];
        aggregate.toArray(matches);
        return (matches);
    }

    /**
     * Encapsulation of a matched line from a file. This immutable
     * object has five read-only properties:<ul>
     * <li>getFile( ): The File this match pertains to.</li>
     * <li>getLineNumber( ): The line number (1-relative) within the
     * file where the match was found.</li>
     * <li>getLineText( ): The text of the matching line</li>
     * <li>start( ): The index within the line where the matching
     * pattern begins.</li>
     * <li>end( ): The index, plus one, of the end of the matched
     * character sequence.</li>
     * </ul>
     */
    public static class MatchedLine {
        private File file;
        private int lineNumber;
        private String lineText;
        private int start;
        private int end;

        MatchedLine(File file, int lineNumber, String lineText, int start, int end) {
            this.file = file;
            this.lineNumber = lineNumber;
            this.lineText = lineText;
            this.start = start;
            this.end = end;
        }

        public File getFile() {
            return (this.file);
        }

        public int getLineNumber() {
            return (this.lineNumber);
        }

        public String getLineText() {
            return (this.lineText);
        }

        public int start() {
            return (this.start);
        }

        public int end() {
            return (this.end);
        }
    }

    /**
     * Run the grepper on the given File.
     *
     * @return A (non-type-safe) List of MatchedLine objects.
     */
    private List grepList(File file)
            throws IOException {
        if (!file.exists()) {
            throw new IOException("Does not exist: " + file);
        }
        if (!file.isFile()) {
            throw new IOException("Not a regular file: " + file);
        }
        if (!file.canRead()) {
            throw new IOException("Unreadable file: " + file);
        }
        LinkedList list = new LinkedList();
        FileReader fr = new FileReader(file);
        LineNumberReader lnr = new LineNumberReader(fr);
        Matcher matcher = this.pattern.matcher("");
        String line;
        while ((line = lnr.readLine()) != null) {
            matcher.reset(line);
            if (matcher.find()) {
                list.add(new MatchedLine(file,
                        lnr.getLineNumber(), line,
                        matcher.start(), matcher.end()));
            }
        }
        lnr.close();
        return (list);
    }

    /**
     * Test code to run grep operations. Accepts two command-line
     * options: -i or --ignore-case, compile the given pattern so
     * that case of alpha characters is ignored. Or -1, which runs
     * the grep operation on each individual file, rather that passing
     * them all to one invocation. This is just to test the different
     * methods. The printed ouptut is slightly different when -1 is
     * specified.
     */
    public static void main(String[] argv) {
        // 设置默认值
        boolean ignoreCase = false;
        boolean onebyone = false;
        List argList = new LinkedList(); // 采集变量
        // 循环遍历变量，查找转换并保存模式及文件名
        for (int i = 0; i < argv.length; i++) {
            if (argv[i].startsWith("-")) {
                if (argv[i].equals("-i")
                        || argv[i].equals("--ignore-case")) {
                    ignoreCase = true;
                }
                if (argv[i].equals("-1")) {
                    onebyone = true;
                }
                continue;
            }
            // 不是转移（switch），将其添加到列表中
            argList.add(argv[i]);
        }
        // 是否有足够的变量可以运行？
        if (argList.size() < 2) {
            System.err.println("usage: [options] pattern filename ...");
            return;
        }
        // 列表中第一个变量将被作为正则模式。
        // 将模式及忽略大小写标志的当前值传递给新的Grep对象。
        Grep grepper = new Grep((String) argList.remove(0),
                ignoreCase);
        // 随意点，拆分成调用grep程序和打印结果两种方式
        if (onebyone) {
            Iterator it = argList.iterator();
            // 循环遍历文件名并用grep处理它们
            while (it.hasNext()) {
                String fileName = (String) it.next();
                // 在每次grep前先打印文件名
                System.out.println(fileName + ":");
                MatchedLine[] matches = null;
                // 捕获异常
                try {
                    matches = grepper.grep(fileName);
                } catch (IOException e) {
                    System.err.println("\t*** " + e);
                    continue;
                }
                // 打印匹配行的资料
                for (int i = 0; i < matches.length; i++) {
                    MatchedLine match = matches[i];
                    System.out.println(" "
                            + match.getLineNumber()
                            + " [" + match.start()
                            + "-" + (match.end() - 1)
                            + "]: "
                            + match.getLineText());
                }
            }
        } else {
            // 把文件名列表转换到File阵列中
            File[] files = new File[argList.size()];
            for (int i = 0; i < files.length; i++) {
                files[i] = new File((String) argList.get(i));
            }
            // 运行grep程序；忽略无法读取的文件
            MatchedLine[] matches = grepper.grep(files);
            // 打印匹配行的资料
            for (int i = 0; i < matches.length; i++) {
                MatchedLine match = matches[i];
                System.out.println(match.getFile().getName() + ", " + match.getLineNumber() + ": " + match.getLineText());
            }
        }
    }
}