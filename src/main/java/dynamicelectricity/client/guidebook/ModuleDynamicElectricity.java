package dynamicelectricity.client.guidebook;

import dynamicelectricity.DynamicElectricity;
import dynamicelectricity.client.guidebook.chapters.ChapterACMotors;
import dynamicelectricity.client.guidebook.chapters.ChapterDCMotors;
import dynamicelectricity.client.guidebook.chapters.ChapterDyanmicElectricity;
import dynamicelectricity.core.utils.UtilsText;
import net.minecraft.network.chat.MutableComponent;
import voltaic.client.guidebook.utils.components.Module;
import voltaic.client.guidebook.utils.pagedata.graphics.ImageWrapperObject;

public class ModuleDynamicElectricity extends Module {

    private static final ImageWrapperObject LOGO = new ImageWrapperObject(0, 0, 0, 0, 32, 32, 32, 32, DynamicElectricity.rl("textures/screen/guidebook/dynamicelectricitylogo.png"));

    @Override
    public ImageWrapperObject getLogo() {
        return LOGO;
    }

    @Override
    public MutableComponent getTitle() {
        return UtilsText.guidebook(DynamicElectricity.ID);
    }

    @Override
    public void addChapters() {
        chapters.add(new ChapterDyanmicElectricity(this));
        chapters.add(new ChapterACMotors(this));
        chapters.add(new ChapterDCMotors(this));
    }

}
