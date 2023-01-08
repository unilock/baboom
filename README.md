# baboom

(explosion sfx)

`baboom.json`:
```json5
{
  // Comma-separated UUID(s) of explosive players. Blank for all.
  // Example: "123e4567-e89b-12d3-a456-426614174000"
  "targetUuids": ["uuid-one","uuid-two","..."],

  // Comma-separated dimension(s) in which the player(s) will explode. Blank for any.
  // Format: "namespace:path"
  // Default: ["minecraft:the_nether"]
  "targetDimensions": ["minecraft:the_nether"],

  // Whether the explosion creates fire.
  // Default: true
  "createFire": true,

  // Explosion power.
  // Examples:
  // - TNT = 4.0
  // - Wither spawn = 7.0
  // Default: 4.0
  "explosionPower": 4.0
}
```
