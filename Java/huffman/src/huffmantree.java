/**
 * Created by song on 2016. 11. 11..
 */
public class huffmantree {
    char character;
    int frequency;
    huffmantree left = null;
    huffmantree right= null;
    public huffmantree(){
        character = '\0';
    }
    public huffmantree(char ch, int freq){
        character = ch;
        frequency = freq;
    }

    public char getCharacter() {
        return character;
    }

    public void setCharacter(char character) {
        this.character = character;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public huffmantree getLeft() {
        return left;
    }

    public void setLeft(huffmantree left) {
        this.left = left;
    }

    public huffmantree getRight() {
        return right;
    }

    public void setRight(huffmantree right) {
        this.right = right;
    }
    public String toString(){
        if(left!=null && right!=null)
            return "[ char : "+character +", frequency : " +frequency +"]\n"+left.toString()+right.toString();
        else if(left!=null)
            return "[ char : "+character +", frequency : " +frequency +"]\n"+left.toString();
        else if(right != null)
            return "[ char : "+character +", frequency : " +frequency +"]\n"+right.toString();
        else
            return "[ char : "+character +", frequency : " +frequency +"]\n";
    }
}
