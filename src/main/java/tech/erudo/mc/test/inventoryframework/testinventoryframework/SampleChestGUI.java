package tech.erudo.mc.test.inventoryframework.testinventoryframework;

import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.OutlinePane;
import com.github.stefvanschie.inventoryframework.pane.PaginatedPane;
import com.github.stefvanschie.inventoryframework.pane.PatternPane;
import com.github.stefvanschie.inventoryframework.pane.util.Pattern;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * ページの切り替えができるGUI
 */
public class SampleChestGUI extends ChestGui {

    private int currentPage = 0;

    public SampleChestGUI() {
        super(6, "SampleChestGui");

        setup();
    }

    /**
     *
     */
    private void setup() {
        //各ページの作成
        OutlinePane page1 = new OutlinePane(0, 0, 9, 5);
        OutlinePane page2 = new OutlinePane(0, 0, 9, 5);
        OutlinePane page3 = new OutlinePane(0, 0, 9, 5);
        OutlinePane page4 = new OutlinePane(0, 0, 9, 5);

        //各ページにアイテムをセット
        for (int i = 0; i < 9 * 5; i++) {
            page1.addItem(new GuiItem(new ItemStack(Material.STONE)));
            page2.addItem(new GuiItem(new ItemStack(Material.GRASS_BLOCK)));
            page3.addItem(new GuiItem(new ItemStack(Material.OAK_PLANKS)));
            page4.addItem(new GuiItem(new ItemStack(Material.END_STONE)));
        }

        //PaginatedPaneに各ページのPaneを追加
        PaginatedPane paginatedPane = new PaginatedPane(0, 0, 9, 5);
        paginatedPane.addPane(0, page1);
        paginatedPane.addPane(1, page2);
        paginatedPane.addPane(2, page3);
        paginatedPane.addPane(3, page4);

        //ページ移動および閉じるボタンのUI部分をパターンをもとに作成
        Pattern pattern = new Pattern(
                "010020030"
        );
        PatternPane patternPane = new PatternPane(0, 5, 9, 1, pattern);
        patternPane.bindItem('0', new GuiItem(new ItemStack(Material.GRAY_STAINED_GLASS_PANE)));

        //ページ移動用のアイテム作成
        ItemStack right = new ItemStack(Material.OAK_BUTTON);
        ItemMeta rightMeta = right.getItemMeta();
        rightMeta.displayName(Component.text("→"));
        right.setItemMeta(rightMeta);

        ItemStack left = new ItemStack(Material.OAK_BUTTON);
        ItemMeta leftMeta = left.getItemMeta();
        leftMeta.displayName(Component.text("←"));
        left.setItemMeta(leftMeta);

        patternPane.bindItem('1', new GuiItem(left, event -> {
            if(currentPage > 0) {
                paginatedPane.setPage(currentPage--);
                this.update();
            }
        }));

        patternPane.bindItem('2', new GuiItem(new ItemStack(Material.BARRIER), event -> {
            this.getInventory().close();
        }));

        patternPane.bindItem('3', new GuiItem(right, event -> {
            if(currentPage < 3) {
                paginatedPane.setPage(currentPage++);
                this.update();
            }
        }));

        this.addPane(paginatedPane);
        this.addPane(patternPane);
    }


}
