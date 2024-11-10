# Sean's Guinea Pigs

## Mod Compatibility

Adding support for mods (or other vanilla features) is extremely easy and can be done yourself through the use of[
data packs](https://minecraft.wiki/w/Data_pack).

To add new food items, create a file named `guinea_pig_food.json` in the `data/sgp/tags/item` directory. Inside that
file you will add the identifiers of the new items.

**Note: Make sure to include `"replace": false` so the default food
items do not get overwritten by your data pack.**

```json
{
  "replace": false,
  "values": [
    "minecraft:diamond"
  ]
}
```

Then package this up like a normal data pack and put it into your `worlds` folder.

If you need more assistance, you can ask for help on
the [discussions page](https://github.com/seaneoo/sgp/discussions/categories/help).
