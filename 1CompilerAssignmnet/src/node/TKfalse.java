/* This file was generated by SableCC (http://www.sablecc.org/). */

package node;

import analysis.*;

@SuppressWarnings("nls")
public final class TKfalse extends Token
{
    public TKfalse()
    {
        super.setText("false");
    }

    public TKfalse(int line, int pos)
    {
        super.setText("false");
        setLine(line);
        setPos(pos);
    }

    @Override
    public Object clone()
    {
      return new TKfalse(getLine(), getPos());
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseTKfalse(this);
    }

    @Override
    public void setText(@SuppressWarnings("unused") String text)
    {
        throw new RuntimeException("Cannot change TKfalse text.");
    }
}
