package dynamicelectricity.client.guidebook.chapters;

import com.maciej916.indreb.common.item.ModItems;

import dynamicelectricity.core.utils.UtilsText;
import electrodynamics.client.guidebook.utils.components.Chapter;
import electrodynamics.client.guidebook.utils.components.Module;
import electrodynamics.client.guidebook.utils.pagedata.graphics.ItemWrapperObject;
import electrodynamics.client.guidebook.utils.pagedata.text.TextWrapperObject;
import net.minecraft.network.chat.MutableComponent;

public class ChapterIndustrialReborn extends Chapter {

	private static final ItemWrapperObject LOGO = new ItemWrapperObject(7, 10, 32, 32, 32, 2.0F, ModItems.ALLOY_SMELTER.get());
	
	public ChapterIndustrialReborn(Module module) {
		super(module);
	}

	@Override
	public ItemWrapperObject getLogo() {
		return LOGO;
	}

	@Override
	public MutableComponent getTitle() {
		return UtilsText.guidebook("chapter.industrialreborn");
	}
	
	@Override
	public void addData() {
		pageData.add(new TextWrapperObject(UtilsText.guidebook("chapter.industrialreborn.l1")).setIndentions(1));
		pageData.add(new TextWrapperObject(UtilsText.guidebook("chapter.industrialreborn.lv")).setIndentions(1).setSeparateStart());
		pageData.add(new TextWrapperObject(UtilsText.guidebook("chapter.industrialreborn.mv")).setIndentions(1).setSeparateStart());
		pageData.add(new TextWrapperObject(UtilsText.guidebook("chapter.industrialreborn.hv")).setIndentions(1).setSeparateStart());
		pageData.add(new TextWrapperObject(UtilsText.guidebook("chapter.industrialreborn.l2")).setSeparateStart());
	}

}
