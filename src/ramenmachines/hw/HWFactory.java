package ramenmachine.hw;

public class HWFactory {
	public static HWInterface createHWBoiler() {
		return new HWBoiler();
	}
	
	public static HWInterface createHWWaterTank() {
		return new HWWaterTank();
	}
	
	public static HWInterface createHWInductionHeater() {
		return new HWInductionHeater();
	}
	
	public static HWInterface createHWIngredient() {
		return new HWIngredient();
	}
}
