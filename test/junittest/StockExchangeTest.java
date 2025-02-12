package junittest;



import org.junit.Assert;
import org.junit.Test;

import com.exchrategen.ExchRateGen;
import com.share.Share;
import com.sharecollection.ShareCollection;
import com.user.User;
import com.usercollection.UserCollection;





public class StockExchangeTest {
	//Test1: Azt tesztelem, hogy két felhasználó megegyezik-e, ezáltal ha végigfutom a kollekciót benne van-e.
	@Test
	public void testUserExist() {
		User user1 = new User("Lajos", "jelszo", 1000);
		User user2 = new User("Lajos", "jelszo", 1000);
		Assert.assertTrue(user1.userExist(user2));
	}
	//Test2: Azt tesztelem, hogyha van elég pénz, akkor sikeres-e a vásárlás 
	@Test
	public void testAddPositionSuccess() {
		User user = new User("Lajos", "jelszo", 100000);
		Share share = new Share("Apple","APPL",2000);
		user.addPosition(share, 1, true);
		Assert.assertNotEquals(0, user.getPositions().size());
	}
	//Test3: Azt tesztelem, hogyha nincs elég pénz, akkor sikeres-e a vásárlás 
	@Test
	public void testAddPositionFail() {
		User user = new User("Lajos", "jelszo", 1000);
		Share share = new Share("Apple","APPL",2000);
		user.addPosition(share, 10, true);
		Assert.assertEquals(0, user.getPositions().size());
	}
	//Test4: Azt tesztelem, hogyha duplájára nõtt a részvény árfolyama és én vettem a részvényt(nem shortoltam), akkor zárásnál a pénzem is duplájára nõ-e.
	@Test
	public void testClosePositionBuy() {
		User user = new User("Lajos", "jelszo", 10000);
		Share share = new Share("Apple","APPL",2000);
		user.addPosition(share, 5, true);
		share.setValue(4000);
		user.closePosition(user.getPositions().get(0), share);
		Assert.assertEquals(20000, user.getCashavailable());
	}
	//Test5: Azt tesztelem, hogyha duplájára nõtt a részvény árfolyama és én eladtam a részvényt(shortoltam), akkor zárásnál a pénzem eltûnik-e (vagyis 0-a lesz).
	@Test
	public void testClosePositionSell() {
		User user = new User("Lajos", "jelszo", 10000);
		Share share = new Share("Apple","APPL",2000);
		user.addPosition(share, 5, false);
		share.setValue(4000);
		user.closePosition(user.getPositions().get(0), share);
		Assert.assertEquals(0, user.getCashavailable());
	}
	
	
	//Test6: Azt tesztelem, hogy hogy két részvény megegyezik-e, ezáltal ha végigfutom a kollekciót benne van-e
	@Test
	public void testShareExist() {
		Share share1 = new Share("Apple","APPL",2000);
		Share share2 = new Share("Apple","APPL",2000);
		Assert.assertTrue(share1.shareExist(share2));
	}
	//Test7: Azt tesztelem, hogy rögzítésre kerül-e a legutolsó árfolyama a részvénynek az elõzményekben.
	@Test
	public void testAddLastValue() {
		Share share = new Share("Apple","APPL",2000);
		share.addLastValue(1000);
		Assert.assertNotEquals(0, share.getHistory().size());
	}
	
	//Test8: Azt tesztelem, hogy a részvény felvitel sikeres-e.
	@Test
	public void testAddShare() {
		ShareCollection sc = new ShareCollection();
		Share share1 = new Share("Apple","APPL",2000);
		Share share2 = new Share("Microsoft","MIC",2000);
		sc.addShare(share1);
		sc.addShare(share2);
		Assert.assertEquals(2,sc.getSharecollection().size());
	}
	//Test9: Azt tesztelem, hogy a részvény eltávolítás sikeres-e.
	@Test
	public void testRemoveShare() {
		ShareCollection sc = new ShareCollection();
		Share share1 = new Share("Apple","APPL",2000);
		Share share2 = new Share("Microsoft","MIC",2000);
		sc.addShare(share1);
		sc.addShare(share2);
		sc.removeShare(share1);
		Assert.assertEquals(1,sc.getSharecollection().size());
	}
	
	
	//Test10: Azt tesztelem, hogy a felhasználó felvitel sikeres-e.
	@Test
	public void testAddUser() {
		UserCollection uc = new UserCollection();
		User user1 = new User("Lajos", "jelszo", 1000);
		User user2 = new User("Béla", "masjelszo", 1000);
		uc.addUser(user1);
		uc.addUser(user2);
		Assert.assertEquals(2,uc.getUsercollection().size());
	}
	//Test11: Azt tesztelem, hogy a felhasználó eltávolítás sikeres-e.
	@Test
	public void testRemoveUser() {
		UserCollection uc = new UserCollection();
		User user1 = new User("Lajos", "jelszo", 1000);
		User user2 = new User("Béla", "masjelszo", 1000);
		uc.addUser(user1);
		uc.addUser(user2);
		uc.removeUser(user1);
		Assert.assertEquals(1,uc.getUsercollection().size());
	}
	
	
	//Test12: Azt tesztelem, hogy az árfolyam generátor valóban [0,1] intervallumon generál-e.
	@Test
	public void testGenerateRate() {
		ExchRateGen gen = new ExchRateGen();
		double rate = gen.generateRate();
		boolean bool=false;
		if(rate<=1&&rate>=0) {
			bool=true;
		}
		Assert.assertTrue(bool);
	}
	
	
	
	
	
	
	
	
	
	
	
}
