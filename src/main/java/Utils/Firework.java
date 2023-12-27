package Utils;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;

import java.util.ArrayList;
import java.util.Random;

public class Firework {

    private final Player p;
    private final int level_needed;

    public Firework(Player p, int level_needed){
        this.p = p;
        this.level_needed = level_needed;
    }

    private ArrayList<Integer> blc(String face, int i){
        ArrayList<Integer> list = new ArrayList<Integer>();
        switch (face){
            case "NORTH":
            {
                list.add(0);
                list.add(-i);
            }
            break;
            case "SOUTH":{
                list.add(0);
                list.add(i);
            }
            break;
            case "WEST": {
                list.add(-i);
                list.add(0);
            }
            case "EAST": {
                list.add(i);
                list.add(0);
            }
            default:
                break;
        }

        return  list;
    }

    public void launch(){
        for(int i = 0; i < (level_needed*0.2); ++i){

            ArrayList<Integer> ai = blc(p.getFacing().toString(), i);

            org.bukkit.entity.Firework firework = p.getWorld().spawn(p.getEyeLocation().add(ai.get(0), 0, ai.get(1)), org.bukkit.entity.Firework.class);
            FireworkMeta data = (FireworkMeta) firework.getFireworkMeta();

            //Our random generator
            Random r = new Random();

            //Get the type
            int rt = r.nextInt(5)+1;
            FireworkEffect.Type type = FireworkEffect.Type.BALL;
            if (rt == 1) type = FireworkEffect.Type.BALL;
            if (rt == 2) type = FireworkEffect.Type.BALL_LARGE;
            if (rt == 3) type = FireworkEffect.Type.BURST;
            if (rt == 4) type = FireworkEffect.Type.CREEPER;
            if (rt == 5) type = FireworkEffect.Type.STAR;

            //Get our random colours
            int r1i = r.nextInt(16) + 1;
            int r2i = r.nextInt(16) + 1;
            Color c1 = getColor(r1i);
            Color c2 = getColor(r2i);

            //Create our effect with this
            data.addEffects(FireworkEffect.builder().flicker(r.nextBoolean()).withColor(c1).withFade(c2).with(type).trail(r.nextBoolean()).build());

            //Generate some random power and set it
            int rp = r.nextInt(2) + 1;
            data.setPower(rp);

            //Then apply the effect to the meta
            firework.setFireworkMeta(data);
        }
    }
    private Color getColor(int colorIdx) {
        ArrayList<Color> arr = new ArrayList<>();

        arr.add(Color.GREEN);
        arr.add(Color.RED);
        arr.add(Color.BLUE);
        arr.add(Color.LIME);
        arr.add(Color.AQUA);
        arr.add(Color.ORANGE);
        arr.add(Color.WHITE);
        arr.add(Color.PURPLE);
        arr.add(Color.MAROON);
        arr.add(Color.GRAY);
        arr.add(Color.BLACK);
        arr.add(Color.FUCHSIA);
        arr.add(Color.OLIVE);
        arr.add(Color.NAVY);
        arr.add(Color.TEAL);
        arr.add(Color.YELLOW);
        arr.add(Color.SILVER);

        return arr.get(colorIdx);
    }
}
