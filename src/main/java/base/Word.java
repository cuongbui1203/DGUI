package base;
/**
 * cau truc cua tu dc luu
 */
public class Word {
    public String word_target;
    public String word_explain;
    public String word_pa;
    public String code_html;

    public Word(){
    }
    public Word(String word_target,String word_explain){
        this.word_target = word_target;
        this.word_explain = word_explain;
    }

    public Word(String word_target,String word_pa,String word_explain,String code_html){
        this.word_target = word_target;
        this.word_explain = word_explain;
        this.word_pa = word_pa;
        this.code_html = code_html;
    }

}
