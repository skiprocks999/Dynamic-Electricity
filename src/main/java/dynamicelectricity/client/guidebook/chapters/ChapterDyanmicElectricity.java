package dynamicelectricity.client.guidebook.chapters;

import dynamicelectricity.core.utils.UtilsText;
import dynamicelectricity.registry.DynamicElectricityItems;
import electrodynamics.client.guidebook.utils.components.Chapter;
import electrodynamics.client.guidebook.utils.components.Module;
import electrodynamics.client.guidebook.utils.pagedata.graphics.ItemWrapperObject;
import electrodynamics.client.guidebook.utils.pagedata.text.TextWrapperObject;
import net.minecraft.util.text.IFormattableTextComponent;

public class ChapterDyanmicElectricity extends Chapter {

	private static final ItemWrapperObject LOGO = new ItemWrapperObject(7, 10, 32, 32, 32, 2.0F, DynamicElectricityItems.ITEM_STATOR.get());

	public ChapterDyanmicElectricity(Module module) {
		super(module);
	}

	@Override
	public ItemWrapperObject getLogo() {
		return LOGO;
	}

	@Override
	public IFormattableTextComponent getTitle() {
		return UtilsText.guidebook("chapter.dynamicelectricity");
	}

	@Override
	public void addData() {
		pageData.add(new TextWrapperObject(UtilsText.guidebook("chapter.dynamicelectricity.l1")).setIndentions(1));
	}

}
