# API Spec

## CONFIGURATION MOTEUR DE JEU

/applications POST créé une application
{
    name: <name>,
    url: <url>,
    apitoken: <token>,
    description: <description>
}

/badges POST créé un badge
{
    name: <name>,
    image: <img>,
    description: <description>,
}

/pointScales POST créé une échelle de point
{
    name: <name>,
    description: <description>
}
scales: activity, reputation

/rules POST créé un règle
{
    eventType: <type>,
    awardBadge: <badge>,
    awardPoints: {
        pointScale: <scale>
        amount: <amount>
    }
}

eventType: addQuestion, voteQuestion, answerQuestion

## EVENTS
/events POST déclanche un event
{
    userId: <user>,
    timestamp: <datetime>,
    type: <eventType>,
    properties: {
        stuff
    }
}
  
voteQuestion:
affecte l'utilisateur subissant le vote (i.e upvote +points, downvote -points)
properties: {
    type: <upvote|downvote>
}

## CLIENT APP GET

/users/{id}/badges GET récupère les badges d'un user
/scales/{id}/leaderboards GET

