# Spring Boot 2.x
> Spring Boot 사용법 및 정의
## Repository 구조
* 자주 사용하는 용도의 Branch
    * master
        * 가장 자주 사용하는 구성의 Branch
    * feature/XXX
        * master Branch의 기능 개발 Branch
* 특정 목적을 위해 사용 되는 Branch
    * master_XXX
        * 특정 목적의 Master Branch
    * master_XXX/feature/XXX
        * master Branch 관련 하위 기능 개발 Branch
        * master_XXX 상위 Branch 로만 Merge 가능