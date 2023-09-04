package dynamicelectricity.client.guidebook;

import dynamicelectricity.References;
import dynamicelectricity.client.guidebook.chapters.ChapterACMotors;
import dynamicelectricity.client.guidebook.chapters.ChapterDCMotors;
import dynamicelectricity.client.guidebook.chapters.ChapterDyanmicElectricity;
import dynamicelectricity.client.guidebook.chapters.ChapterIndustrialReborn;
import dynamicelectricity.core.utils.UtilsText;
import electrodynamics.client.guidebook.utils.components.Module;
import electrodynamics.client.guidebook.utils.pagedata.graphics.ImageWrapperObject;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.ModList;

public class ModuleDynamicElectricity extends Module {

	private static final ImageWrapperObject LOGO = new ImageWrapperObject(0, 0, 0, 0, 32, 32, 32, 32, new ResourceLocation(References.ID, "textures/screen/guidebook/dynamicelectricitylogo.png"));

	@Override
	public ImageWrapperObject getLogo() {
		return LOGO;
	}

	@Override
	public MutableComponent getTitle() {
		return UtilsText.guidebook(References.ID);
	}

	@Override
	public void addChapters() {
		chapters.add(new ChapterDyanmicElectricity(this));
		chapters.add(new ChapterACMotors(this));
		chapters.add(new ChapterDCMotors(this));
		if (ModList.get().isLoaded(References.INDUSTRIAL_REBORN_ID)) {
			chapters.add(new ChapterIndustrialReborn(this));
		}
	}

}
