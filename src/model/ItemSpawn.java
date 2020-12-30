package model;


	

	public class ItemSpawn extends Tile{
		
		public ItemSpawn(String in_source) {
			this.nature=7;
			this.canWalkOn=true;
			this.canFinishGame=false;
			this.nextlevel=false;
			this.skin=super.findSource(in_source);
		}
	}



