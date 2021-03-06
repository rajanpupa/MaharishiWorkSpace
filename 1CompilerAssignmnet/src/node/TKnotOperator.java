/* This file was generated by SableCC (http://www.sablecc.org/). */

package node;

import analysis.*;

@SuppressWarnings("nls")
public final class TKnotOperator extends Token
{
    public TKnotOperator()
    {
        super.setText("!");
    }

    public TKnotOperator(int line, int pos)
    {
        super.setText("!");
        setLine(line);
        setPos(pos);
    }

    @Override
    public Object clone()
    {
      return new TKnotOperator(getLine(), getPos());
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseTKnotOperator(this);
    }

    @Override
    public void setText(@SuppressWarnings("unused") String text)
    {
        throw new RuntimeException("Cannot change TKnotOperator text.");
    }
}
