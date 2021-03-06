/* This file was generated by SableCC (http://www.sablecc.org/). */

package node;

import analysis.*;

@SuppressWarnings("nls")
public final class TKwhile extends Token
{
    public TKwhile()
    {
        super.setText("while");
    }

    public TKwhile(int line, int pos)
    {
        super.setText("while");
        setLine(line);
        setPos(pos);
    }

    @Override
    public Object clone()
    {
      return new TKwhile(getLine(), getPos());
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseTKwhile(this);
    }

    @Override
    public void setText(@SuppressWarnings("unused") String text)
    {
        throw new RuntimeException("Cannot change TKwhile text.");
    }
}
