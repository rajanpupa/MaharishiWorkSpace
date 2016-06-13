/* This file was generated by SableCC (http://www.sablecc.org/). */

package node;

import analysis.*;

@SuppressWarnings("nls")
public final class TKcontinue extends Token
{
    public TKcontinue()
    {
        super.setText("continue");
    }

    public TKcontinue(int line, int pos)
    {
        super.setText("continue");
        setLine(line);
        setPos(pos);
    }

    @Override
    public Object clone()
    {
      return new TKcontinue(getLine(), getPos());
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseTKcontinue(this);
    }

    @Override
    public void setText(@SuppressWarnings("unused") String text)
    {
        throw new RuntimeException("Cannot change TKcontinue text.");
    }
}
