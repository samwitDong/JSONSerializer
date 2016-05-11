package org.samwit.tools.json.serializer.samples.demodata;

import org.samwit.tools.json.serializer.JSONBean;

public class TDB<X, Y> extends JSONBean {

    private X x;
    
    private Y y;
    
    public TDB() {
    }

    public TDB(X x, Y y) {
        super();
        this.x = x;
        this.y = y;
    }

    public X getX() {
        return x;
    }

    public void setX(X x) {
        this.x = x;
    }

    public Y getY() {
        return y;
    }

    public void setY(Y y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof TDB)) {
            return false;
        }
        @SuppressWarnings("rawtypes")
        TDB other = (TDB) obj;
        if (x == null) {
            if (other.x != null) {
                return false;
            }
        } else if (!x.equals(other.x)) {
            return false;
        }
        if (y == null) {
            if (other.y != null) {
                return false;
            }
        } else if (!y.equals(other.y)) {
            return false;
        }
        return true;
    }
}
