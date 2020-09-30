# GameTypes
| ID | Type Name      | Database Name | Clean Name           |
|----|----------------|---------------|----------------------|
| 2  | QUAKECRAFT     | Quake         | Quake                |
| 3  | WALLS          | Walls         | Walls                |
| 4  | PAINTBALL      | Paintball     | Paintball            |
| 5  | SURVIVAL_GAMES | HungerGames   | Blitz Survival Games |
| 6  | TNTGAMES       | TNTGames      | TNT Games            |
| 7  | VAMPIREZ       | VampireZ      | VampireZ             |
| 13 | WALLS3         | Walls3        | Mega Walls           |
| 14 | ARCADE         | Arcade        | Arcade               |
| 17 | ARENA          | Arena         | Arena                |
| 20 | UHC            | UHC           | UHC Champions        |
| 21 | MCGO           | MCGO          | Cops and Crims       |
| 23 | BATTLEGROUND   | Battleground  | Warlords             |
| 24 | SUPER_SMASH    | SuperSmash    | Smash Heroes         |
| 25 | GINGERBREAD    | GingerBread   | Turbo Kart Racers    |
| 26 | HOUSING        | Housing       | Housing              |
| 51 | SKYWARS        | SkyWars       | SkyWars              |
| 52 | TRUE_COMBAT    | TrueCombat    | Crazy Walls          |
| 54 | SPEED_UHC      | SpeedUHC      | Speed UHC            |
| 55 | SKYCLASH       | SkyClash      | SkyClash             |
| 56 | LEGACY         | Legacy        | Classic Games        |
| 57 | PROTOTYPE      | Prototype     | Prototype            |
| 58 | BEDWARS        | Bedwars       | Bed Wars             |
| 59 | MURDER_MYSTERY | MurderMystery | Murder Mystery       |
| 60 | BUILD_BATTLE   | BuildBattle   | Build Battle         |
| 61 | DUELS          | Duels         | Duels                |
| 63 | SKYBLOCK       | SkyBlock      | SkyBlock             |
| 64 | PIT            | Pit           | Pit                  |

### Storage
Games store their respective stats and data in a Player's `stats` collection. The game's specific data is held within a JSON object named after it's `Database Name` (seen above.)

### Notes
* Friendly names are what is displayed to the user when referencing the name.
* Type names or ids are used when the API references a specific GameType.
