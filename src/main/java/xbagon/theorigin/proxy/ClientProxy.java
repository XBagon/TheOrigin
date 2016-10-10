package xbagon.theorigin.proxy;

import xbagon.theorigin.init.ModItems;

public class ClientProxy implements CommonProxy{

	@Override
	public void init() {
		ModItems.registerRenders();
	}

}
