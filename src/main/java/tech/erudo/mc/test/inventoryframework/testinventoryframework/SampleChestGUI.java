package tech.erudo.mc.test.inventoryframework.testinventoryframework;

import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.OutlinePane;
import com.github.stefvanschie.inventoryframework.pane.PaginatedPane;
import com.github.stefvanschie.inventoryframework.pane.PatternPane;
import com.github.stefvanschie.inventoryframework.pane.util.Pattern;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

/**
 * ページの切り替えができるGUI
 */
public class SampleChestGUI extends ChestGui {
    public SampleChestGUI() {
        super(6, "SampleChestGui");
        setup();
    }

    private void setup() {
        //各ページの作成
        OutlinePane page1 = new OutlinePane(0, 0, 9, 5);
        OutlinePane page2 = new OutlinePane(0, 0, 9, 5);
        OutlinePane page3 = new OutlinePane(0, 0, 9, 5);
        OutlinePane page4 = new OutlinePane(0, 0, 9, 5);

        //各ページにアイテムをセット
        for(int i=0; i < 9*5; i++) {
            page1.addItem(new GuiItem(new ItemStack(Material.STONE)));
            page2.addItem(new GuiItem(new ItemStack(Material.GRASS_BLOCK)));
            page3.addItem(new GuiItem(new ItemStack(Material.OAK_PLANKS)));
            page4.addItem(new GuiItem(new ItemStack(Material.END_STONE)));
        }

        //PaginatedPaneに各ページのPaneを追加
        PaginatedPane paginatedPane = new PaginatedPane(0,0,9,5);
        paginatedPane.addPane(0, page1);
        paginatedPane.addPane(1, page2);
        paginatedPane.addPane(2, page3);
        paginatedPane.addPane(3, page4);

        //ページ移動および閉じるボタンのUI部分をパターンをもとに作成
        Pattern pattern = new Pattern(
                "010020030"
        );
        PatternPane patternPane = new PatternPane(0,0,9,1, pattern);
        patternPane.bindItem('0', new GuiItem(new ItemStack(Material.GRAY_STAINED_GLASS_PANE)));
        //ページ移動用のプレイヤーヘッドを作成



        this.addPane(paginatedPane);
    }

    /**
     * プレイヤーヘッドを返すメソッド
     * @param player: プレイヤーヘッドを生成したいプレイヤー
     * @return: 引数で指定したプレイヤーの頭
     */
    public ItemStack getPlayerHead(OfflinePlayer player) {
        ItemStack head = new ItemStack(Material.PLAYER_HEAD);
        ItemMeta meta = head.getItemMeta();
        if(meta instanceof SkullMeta) {
            ((SkullMeta) meta).setOwningPlayer(player);
            head.setItemMeta(meta);
        }

        return head;
    }


}
