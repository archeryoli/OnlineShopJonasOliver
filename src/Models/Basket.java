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
        if(_basketHashMap != null){
            this._basketHashMap = _basketHashMap;
        }
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

    public String toString() {
        return "Basket(_basketId=" + this.getBasketId() + ", _basketHashMap=" + this.getBasketHashMap() + ")";
    }
}
