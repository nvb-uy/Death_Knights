## 1.0.0

IMPORTANT FOR MOD AND MODPACK DEVELOPERS:
THIS UPDATE REPLACES ALL DEATH KNIGHTS ATTRIBUTES WITH THEIR ETERNAL ATTRIBUTES EQUIVALENT
E.G. death_knights:blood > eternal_attributes:blood

If you are referencing death knight's SpellSchoolRegistry class, then the spell schools will be redirected to Eternal Attributes, but for datapacks, you'll need to change the attribute and school ids to eternal_attributes:{school}.

- Now requires Eternal Attributes as dependency
- Added Tier 3 Armor Sets:
  - Icebound Knight (Frost)
  - Crimson Knight (Blood)
  - Blightbringer (Unholy)
- Death's Call armor set now gives +7.5% of all death knight elements instead of flat +1
- Blood Boil's "Blood Plague" no longer deals damage, and instead it makes the death knight heal every time they deal blood damage (such as from a blood ability). The % max health healing scales up with the amount of blood power the death knight has.
- Fixed Decaying enchantment not working