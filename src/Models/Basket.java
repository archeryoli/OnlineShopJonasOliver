package Models;

import java.util.HashMap;

public class Basket {
    private int _basketId;
    private HashMap<Article, Integer> _basketHashMap = new HashMap<>();

    public Basket() {
    }

    public int getBasketId() {
        return this._basketId;
    }

    public HashMap<Article, Integer> getBasketHashMap() {
        return this._basketHashMap;
    }

    public void setBasketId(int _basketId) {
        this._basketId = _basketId;
    }

    public void setBasketHashMap(HashMap<Article, Integer> _basketHashMap) {
        this._basketHashMap = _basketHashMap;
    }
    public void addBasketHashMapEntry(Article articleToAdd, int articleCount){
        _basketHashMap.put(articleToAdd, articleCount);
    }
    public void removeBasketHashMapEntry(Article articleToRemove){
        if(_basketHashMap.containsKey(articleToRemove)){
            _basketHashMap.remove(articleToRemove);
        }
    }
    public void updateHashMapEntry(Article articleToUpdate, int newAmount){
        if(_basketHashMap.containsKey(articleToUpdate) && newAmount > 1){
            addBasketHashMapEntry(articleToUpdate, newAmount);
        }
        if(newAmount == 0){
            _basketHashMap.remove(articleToUpdate);
        }
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Basket)) return false;
        final Basket other = (Basket) o;
        if (!other.canEqual((Object) this)) return false;
        if (this.getBasketId() != other.getBasketId()) return false;
        final Object this$_basketHashMap = this.getBasketHashMap();
        final Object other$_basketHashMap = other.getBasketHashMap();
        if (this$_basketHashMap == null ? other$_basketHashMap != null : !this$_basketHashMap.equals(other$_basketHashMap))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Basket;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + this.getBasketId();
        final Object $_basketHashMap = this.getBasketHashMap();
        result = result * PRIME + ($_basketHashMap == null ? 43 : $_basketHashMap.hashCode());
        return result;
    }

    public String toString() {
        return "Basket(_basketId=" + this.getBasketId() + ", _basketHashMap=" + this.getBasketHashMap() + ")";
    }
}
