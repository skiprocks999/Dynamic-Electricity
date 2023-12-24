package dynamicelectricity.client.guidebook;

import dynamicelectricity.References;
import dynamicelectricity.client.guidebook.chapters.ChapterACMotors;
import dynamicelectricity.client.guidebook.chapters.ChapterDCMotors;
import dynamicelectricity.client.guidebook.chapters.ChapterDyanmicElectricity;
import dynamicelectricity.core.utils.UtilsText;
import electrodynamics.client.guidebook.utils.components.Module;
import electrodynamics.client.guidebook.utils.pagedata.graphics.ImageWrapperObject;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.IFormattableTextComponent;

public class ModuleDynamicElectricity extends Module {

	private static final ImageWrapperObject LOGO = new ImageWrapperObject(0, 0, 0, 0, 32, 32, 32, 32, new ResourceLocation(References.ID, "textures/screen/guidebook/dynamicelectricitylogo.png"));

	@Override
	public ImageWrapperObject getLogo() {
		return LOGO;
	}

	@Override
	public IFormattableTextComponent getTitle() {
		return UtilsText.guidebook(References.ID);
	}

	@Override
	public void addChapters() {
		chapters.add(new ChapterDyanmicElectricity(this));
		chapters.add(new ChapterACMotors(this));
		chapters.add(new ChapterDCMotors(this));
	}

}
