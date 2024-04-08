# PromptsApplication

Dataservive für die Anwendung "frontend-ng-coding-challenge" gebaut in der .. 
- Drei-Schichten-Architektur: Controller -> Service -> Repository
- DTO = Data Transfer Object

### User
```
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

private String username;
private String password;
```

### Prompt

```
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

private String description;

@CreationTimestamp
@Temporal(TemporalType.TIMESTAMP)
@Column(name = "timestamp_time", nullable = false, updatable = false)
private Timestamp timestamp;

@ManyToOne
@JoinColumn(name = "user_id", nullable = false)
private User user;

@OneToMany(mappedBy = "prompt", cascade = CascadeType.ALL)
@JsonIgnoreProperties("prompt")
private List<Like> likes;
```

### Like
```
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

@ManyToOne
private User user;
// Ein Benutzer kann viele Likes tätiges - aber ein Like ist einem bestimmten Nutzer zugeordnet

@ManyToOne
private Prompt prompt;
// ein Prompt kann viele Likes haben - aber ein einzelnes Like ist einem bestimmten Prompt zugeordnet
```


## Voraussetzungen

Bevor Sie beginnen, stellen Sie sicher, dass Sie folgende Software installiert haben:
- Java JDK | 21.0.2
- Docker | Latest Version Docker Desktop

## Installation
### Die folgenden Commands können alle in der Bash ausgeführt werden.
Schritte zur Installation und Einrichtung des Projekts.

1. Klonen Sie das Repository:
```bash
git clone git@github.com:larsn97/dataservice-prompts.git
```
2. Checke die branches
```bash
git branch -a
```
3. Falls nicht von alleine passiert
```bash
git checkout main
```
4. Stelle sicher, dass du auf dem main arbeitest
```bash
git branch
```
``` 
##Output##
$ git branch
* main
```
5. Gradle Build starten
6. Docker Desktop starten
7. Docker Container starten
```bash
docker-compose up
```
8. Run 'PromptsApplication'