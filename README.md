// baboom

(explosion sfx)

`baboom.json`:
```json5
{
  // Comma-separated UUIDs of explosive players.
  // Example: "123e4567-e89b-12d3-a456-426614174000"
  // (you'll need to set this manually after starting the game once, sorry!)
  targetUuids: ["uuid-one","uuid-two","..."],

  // Dimension in which the player will explode. Blank for any.
  // Format: "namespace:path"
  // Default: "minecraft:the_nether"
  "targetDimension": "minecraft:the_nether",

  // Whether the explosion creates fire.
  // Default: true
  "createFire": true,

  // Explosion power.
  // - TNT = 4.0
  // - Wither spawn = 7.0
  // Default: 4.0
  "explosionPower": 4.0
}
```
