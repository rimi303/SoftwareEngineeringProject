public class ExchangeQuery {
    private int answerResId;


    private boolean answerstore;

    public ExchangeQuery(int answerResId, boolean answer)
    {

        this.answerResId = answerResId;
        this.answerstore = answer;
    }


    public int getAnswerResId()
    {
        return answerResId;
    }


    public void setAnswerResId(int answerResId)
    {
        this.answerResId = answerResId;
    }


    public boolean isAnswerTrue()
    {
        return answerstore;
    }


    public void setAnswerTrue(boolean answer)
    {
        this.answerstore = answer;
    }
}



