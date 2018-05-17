# spring-oauth2-jdbc

auth port: 8082
- oauth/token?grant_type=password&username={username}&password={password}
- oauth/token?grant_type=refresh_token&refresh_token={token}
- oauth/token?grant_type=authorization_code&code={code}
- oauth/authorize?response_type=code&client_id={cliendId} 

resource port: 8083
- admin/{text}?access_token={token}
- user/find/{text}?access_token={token}
- user/create/{text}?access_token={token}