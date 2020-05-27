package org.springframework.samples.yogogym.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.yogogym.projections.DashboardAdminChallengesPercentageClients;
import org.springframework.samples.yogogym.projections.DashboardAdminChallengesPercentageGuilds;
import org.springframework.samples.yogogym.projections.DashboardAdminChallengesTopClient;
import org.springframework.samples.yogogym.projections.DashboardAdminChallengesTopGuild;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class DashboardsAdminServiceTest {

	@Autowired
	protected DashboardsAdminService dashboardService;

	@Test
	void shouldCountEquipment() {
		List<Integer> count = this.dashboardService.countEquipment(3000);
		assertThat(count.size()).isEqualTo(6);
	}

	@Test
	void shouldNameEquipment() {
		List<String> count = this.dashboardService.nameEquipment(3000);
		assertThat(count.size()).isEqualTo(6);
	}
	
	@Test
	void shouldCountChallengesOfMonthAndYear(){
		int countChallenges = this.dashboardService.countChallengesOfMonthAndYear(10,2020);
		assertThat(countChallenges).isEqualTo(3);
	}

	@ParameterizedTest
	@ValueSource(ints = {2,4,5,6})
	void shouldNotCountChallengesOfMonthAndYear(int month){
		int countChallenges = this.dashboardService.countChallengesOfMonthAndYear(month,2020);
		assertThat(countChallenges).isEqualTo(0);
	}
	
	@Test
	void shouldCountCompletedInscriptionsOfMonthAndYear(){
		int inscriptions = this.dashboardService.countCompletedInscriptionsOfMonthAndYear(1,2020);
		assertThat(inscriptions).isEqualTo(1);
	}
	
	@Test
	void shouldNotFindCompletedInscriptionsOfMonth(){
		int inscriptions = this.dashboardService.countCompletedInscriptionsOfMonthAndYear(2,2020);
		assertThat(inscriptions).isEqualTo(0);
	}
	
	@Test
	void shouldGetChallengesNamesOfMonthAndYear(){
		String[] challengesNames = this.dashboardService.getChallengesNamesOfMonthAndYear(1, 2020);
		assertThat(challengesNames[0]).isEqualTo("Challenge1");
	}
	
	@Test
	void shouldGetTopClientOfMonthAndYear(){
		DashboardAdminChallengesTopClient topClient = this.dashboardService.getTopClient(1, 2020);
		assertThat(topClient.getUsername()).isEqualTo("Julio Enrique Guerrero");
		assertThat(topClient.getEmail()).isEqualTo("juengue@yogogym.com");
		assertThat(topClient.getPoints()).isEqualTo(10);
	}
	
	@Test
	void shouldGetTopGuildOfMonthAndYear(){
		DashboardAdminChallengesTopGuild topGuild = this.dashboardService.getTopGuild(1, 2020);
		assertThat(topGuild.getGuild()).isEqualTo("Gym for Dummies");
		assertThat(topGuild.getPoints()).isEqualTo(10);
	}
	
	@Test
	void shouldGetPercentageClients() {
		List<DashboardAdminChallengesPercentageClients> pClients = this.dashboardService.getPercentageClients(1, 2020);
		assertThat(pClients.get(0).getChallengeName()).isEqualTo("Challenge1");
		assertThat(pClients.get(0).getPercentageClients()).isEqualTo(0.09090909090909091);
	}
	
	@Test
	void shouldGetPercentageGuilds() {
		List<DashboardAdminChallengesPercentageGuilds> pGuilds = this.dashboardService.getPercentageGuilds(1, 2020);
		assertThat(pGuilds.get(0).getChallengeName()).isEqualTo("Challenge1");
		assertThat(pGuilds.get(0).getPercentageGuilds()).isEqualTo(0.3333333333333333);
	}
	
	
	@Test
	void shouldCountClients() {
		Integer count = this.dashboardService.countClients();
		assertThat(count).isEqualTo(11);
	}
	
	@Test
	void shouldCountTrainers() {
		Integer count = this.dashboardService.countTrainers();
		assertThat(count).isEqualTo(2);
	}
	
	@Test
	void shouldCountClientsPerGuild() {
		List<Integer> list = this.dashboardService.countClientsPerGuild();
		assertThat(list.size()).isEqualTo(4);
	}

}
