package net.tigereye.hellishmaterials.registration;

public class HMStatusEffects {
    /*
    public static StatusEffect HM_BLOODDEBT = new HM_StatusEffect(StatusEffectCategory.HARMFUL, 104562){
        @Override
        public boolean canApplyUpdateEffect(int duration, int amplifier) {
            return duration % BatetDeferment.REPAYMENT_PERIOD == 0;
        }

        @Override
        public void applyUpdateEffect(LivingEntity entity, int amplifier) {
            if(!(entity.world.isClient)){
                BloodDebtInstance instance = (BloodDebtInstance)entity.getStatusEffect(HM_BLOODDEBT);
                float dmg = instance.drawRepayment();
                System.out.println("Repaying "+dmg+" Blood Debt\n");
                BatetDeferment.takeLife(entity,dmg);
                if(instance.getDebt() == 0){
                    System.out.println("Blood Debt Resolved\n");
                }
            }
        }

        @Override
        public void onRemoved(LivingEntity entity, AttributeContainer attributes, int amplifier) {
            BloodDebtInstance instance = (BloodDebtInstance)entity.getStatusEffect(HM_BLOODDEBT);
            if(instance == null){
                System.out.println("Blood Debt is acting strange");
            }
            else if(instance.getDebt() != 0){
                if(entity instanceof PlayerEntity){
                    ((PlayerEntity)entity).sendMessage(new LiteralText("You cannot cheat Death so easily!"),true);
                }
                BatetDeferment.takeLife(entity,instance.getDebt());
            }
        }
    };
    */
    public static void register(){
        //Registry.register(Registry.STATUS_EFFECT, new Identifier(HellishMaterials.MODID, "blood_debt"), HM_BLOODDEBT);
    }
    /*
    public static StatusEffectInstance newBloodDebtStatusEffectInstance(float debt) {
        StatusEffectInstance bloodDebt = new StatusEffectInstance(HMStatusEffects.HM_BLOODDEBT,
                BatetDeferment.REPAYMENT_PERIOD * 1000 - 1, 0, false, true, true);
        ((BloodDebtInstance)bloodDebt).addDebt(debt);
        return bloodDebt;
    }*/
}
