package dynamicelectricity.client.guidebook.chapters;

import dynamicelectricity.common.block.subtype.SubtypeDynamicMachine;
import dynamicelectricity.core.utils.UtilsText;
import dynamicelectricity.registry.DynamicElectricityItems;
import net.minecraft.network.chat.MutableComponent;
import voltaic.client.guidebook.utils.components.Chapter;
import voltaic.client.guidebook.utils.components.Module;
import voltaic.client.guidebook.utils.pagedata.graphics.ItemWrapperObject;
import voltaic.client.guidebook.utils.pagedata.text.TextWrapperObject;

public class ChapterDCMotors extends Chapter {

	private static final ItemWrapperObject LOGO = new ItemWrapperObject(7, 10, 32, 32, 32, 2.0F, DynamicElectricityItems.ITEMS_DYNAMICMACHINE.getValue(SubtypeDynamicMachine.motordchv));

	public ChapterDCMotors(Module module) {
		super(module);
	}

	@Override
	public ItemWrapperObject getLogo() {
		return LOGO;
	}

	@Override
	public MutableComponent getTitle() {
		return UtilsText.guidebook("chapter.dcmotors");
	}

	@Override
	public void addData() {
		pageData.add(new TextWrapperObject(UtilsText.guidebook("chapter.dcmotors.l1")).setIndentions(1));
	}

}
