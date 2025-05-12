package dynamicelectricity.client.guidebook.chapters;

import dynamicelectricity.common.block.subtype.SubtypeDynamicMachine;
import dynamicelectricity.core.utils.UtilsText;
import dynamicelectricity.registry.DynamicElectricityItems;
import net.minecraft.util.text.IFormattableTextComponent;
import voltaic.client.guidebook.utils.components.Chapter;
import voltaic.client.guidebook.utils.components.Module;
import voltaic.client.guidebook.utils.pagedata.graphics.ItemWrapperObject;
import voltaic.client.guidebook.utils.pagedata.text.TextWrapperObject;

public class ChapterACMotors extends Chapter {

	private static final ItemWrapperObject LOGO = new ItemWrapperObject(7, 10, 32, 32, 32, 2.0F, DynamicElectricityItems.ITEMS_DYNAMICMACHINE.getValue(SubtypeDynamicMachine.motorachv));

	public ChapterACMotors(Module module) {
		super(module);
	}

	@Override
	public ItemWrapperObject getLogo() {
		return LOGO;
	}

	@Override
	public IFormattableTextComponent getTitle() {
		return UtilsText.guidebook("chapter.acmotors");
	}

	@Override
	public void addData() {
		pageData.add(new TextWrapperObject(UtilsText.guidebook("chapter.acmotors.l1")).setIndentions(1));
	}

}
