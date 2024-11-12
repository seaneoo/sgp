[![Modrinth Downloads](https://img.shields.io/modrinth/dt/CiE7du5Z?style=flat&logo=modrinth&logoColor=%23ffffff&label=Modrinth&color=%231bd96a)](https://modrinth.com/mod/sgp) [![CurseForge Downloads](https://img.shields.io/curseforge/dt/1138196?style=flat&logo=curseforge&logoColor=%23ffffff&label=CurseForge&color=%23ff784d)](https://www.curseforge.com/minecraft/mc-mods/sgp)

# Sean's Guinea Pigs

![](https://cdn.modrinth.com/data/CiE7du5Z/images/813ae77c09fda419b9af33a27646ef026f5e5368.jpeg)

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
